package com.accenture.ims.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.accenture.ims.exceptions.OrderServiceException;
import com.accenture.ims.model.AccessoryInventory;
import com.accenture.ims.model.CarInventory;
import com.accenture.ims.model.CarStandingOrders;
import com.accenture.ims.model.InsuranceProvider;
import com.accenture.ims.model.SalesEstimate;
import com.accenture.ims.model.TaxRates;
import com.accenture.ims.repository.AccessoryInventoryRepository;
import com.accenture.ims.repository.CarInventoryRepository;
import com.accenture.ims.repository.InsuranceProviderRepository;
import com.accenture.ims.repository.SalesEstimateRepository;
import com.accenture.ims.repository.TaxRatesRepository;
import com.accenture.ims.utils.timelogger.LogExecutionTime;

/**
 * Business logic to process all the orders
 * @author nayan.arora
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	CarInventoryRepository carRepo;
	
	@Autowired
	AccessoryInventoryRepository accessRepo;
	
	@Autowired
	TaxRatesRepository taxRepo;
	
	@Autowired
	InsuranceProviderRepository insuranceRepo;
	
	@Autowired
	SalesEstimateRepository salesRepo;

	@Override
	@Transactional
	@LogExecutionTime
	public List<CarStandingOrders> processOrders(List<CarStandingOrders> orders) throws OrderServiceException {
		List<CarStandingOrders> invalidOrders = new ArrayList<CarStandingOrders>();
		List<CarInventory> availableCars = (List<CarInventory>) carRepo.findAll();
		List<AccessoryInventory> availableAccessories = (List<AccessoryInventory>) accessRepo.findAll();
		List<TaxRates> taxRates = (List<TaxRates>) taxRepo.findAll();
		List<InsuranceProvider> insuranceProviders = (List<InsuranceProvider>) insuranceRepo.findAll();
		try {
			orders.stream().forEach(order -> {
				CarStandingOrders checkedOrder = checkForValidOrder(order,availableCars,availableAccessories,taxRates,insuranceProviders);
				if(checkedOrder.getErrorMessage()==null || checkedOrder.getErrorMessage().isEmpty()) {
					placeOrder(order);
				}else {
					invalidOrders.add(checkedOrder);
				}
			});
		} catch (Exception e) {
			throw new OrderServiceException(e.getMessage(), e);
		}
		return invalidOrders;
	}
	
	/**
	 * To check if a order is valid 
	 * @param order
	 * @param availableCars
	 * @param availableAccessories
	 * @param taxRates
	 * @param insuranceProviders
	 * @return
	 */
	private CarStandingOrders checkForValidOrder(CarStandingOrders order,List<CarInventory> availableCars,List<AccessoryInventory> availableAccessories,
			List<TaxRates> taxRates,List<InsuranceProvider> insuranceProviders) {
		Stream<AccessoryInventory> accessoryStream = null;
		Map<String, AccessoryInventory> accMap = new HashMap<String, AccessoryInventory>();
		Double accessoryCost = 0.0;
		Double taxExpense = 0.0;
		
		try {
			Optional<CarInventory> carFound = availableCars.stream().filter(car -> car.matchCarDetails(order)).findAny();
			
			Optional<TaxRates> regionFound = taxRates.stream().filter(region -> region.getState().equals(order.getRegion())).findAny();
			
			Optional<InsuranceProvider> insuranceProvider = insuranceProviders.stream().filter(provider -> provider.getMotorInsuranceProvider().equals(order.getMotorInsurance())).findAny();
			
			
			accessoryStream = availableAccessories.parallelStream().filter(accessory ->{
				return accessory.matchAccessoryDetails(order);
			}).filter(accessory -> accessory.getQuantity()>0);
			accMap = accessoryStream.collect(Collectors.toMap( accessory -> accessory.getAccessories(),accessory-> accessory));
			
			accessoryCost = getAccessoryCost(order.getAccessories(),accMap);
			
			if(!carFound.isPresent()) {
				order.setErrorMessage("Mismatch with Car Inventory Available");
			}else if(carFound.get().getQuantity()<=0) {
				order.setErrorMessage("Car Inventory unavailable");
			}else if (accMap.isEmpty() || accessoryCost == null) {
				order.setErrorMessage("Mismatch with Accesories Inventory Available");
			}else if (!regionFound.isPresent()) {
				order.setErrorMessage("Region not present");
			}else if (!insuranceProvider.isPresent()) {
				order.setErrorMessage("Insurance Provider not present");
			}else {
				taxExpense = (carFound.get().getBasePrice()  + accessoryCost) * (regionFound.get().getTaxRate()/100);
				order.setTaxExpense(taxExpense);
				order.setSalePrice(carFound.get().getBasePrice()+accessoryCost +insuranceProvider.get().getFirstYearPremium()+taxExpense);
				carFound.get().setQuantity(carFound.get().getQuantity()-1);
				availableAccessories.forEach(accessory -> {
					if(order.getAccessories().contains(accessory.getAccessories())) {
						accessory.setQuantity(accessory.getQuantity()-1);
					}
				});
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return order;
	}
	
	/**
	 * To place order by updating sales estimate
	 * @param order
	 */
	@Async
	private void placeOrder(CarStandingOrders order) {
		try {
			SalesEstimate saleForState = salesRepo.findByRegion(order.getRegion());
			if(saleForState!=null) {
				saleForState.setUnitsSold(saleForState.getUnitsSold()+1);
				saleForState.setTotalSales(saleForState.getTotalSales()+ order.getSalePrice());
				saleForState.setNetIncome(saleForState.getNetIncome() + order.getSalePrice() - order.getTaxExpense());
			}else {
				saleForState = new SalesEstimate();
				saleForState.setUnitsSold(1);
				saleForState.setTotalSales(order.getSalePrice());
				saleForState.setNetIncome(order.getSalePrice() - order.getTaxExpense());
				saleForState.setRegion(order.getRegion());
			}
			logger.info(order.toString());
			salesRepo.save(saleForState);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Calculate accessory cost
	 * Return null if invalid accessory ordered
	 * @param accessoriesOrdered
	 * @param accMap
	 * @return
	 */
	private Double getAccessoryCost(List<String> accessoriesOrdered, Map<String,AccessoryInventory> accMap) {
		Double accessoryCost = 0.0;
		try {
			for (String accessory : accessoriesOrdered) {		
				if(accMap.get(accessory) != null && accMap.get(accessory).getQuantity()>0) {
					accessoryCost = accessoryCost + accMap.get(accessory).getPrice();
				}else {
					accessoryCost = null;
					throw new Exception("Invalid Accessory Ordered");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return accessoryCost;
	}
	
}
