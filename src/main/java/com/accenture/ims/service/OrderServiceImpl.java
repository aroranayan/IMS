package com.accenture.ims.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.ims.model.AccessoryInventory;
import com.accenture.ims.model.CarInventory;
import com.accenture.ims.model.CarStandingOrders;
import com.accenture.ims.repository.AccessoryInventoryRepository;
import com.accenture.ims.repository.CarInventoryRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	CarInventoryRepository carRepo;
	
	@Autowired
	AccessoryInventoryRepository accessRepo;

	@Override
	public List<CarStandingOrders> placeOrders(List<CarStandingOrders> orders) {
		List<CarStandingOrders> invalidOrders = new ArrayList<CarStandingOrders>();
		List<CarInventory> availableCars = (List<CarInventory>) carRepo.findAll();
		List<AccessoryInventory> availableAccessories = (List<AccessoryInventory>) accessRepo.findAll();
		try {
			orders.stream().forEach(order -> {
				CarStandingOrders checkedOrder = checkForValidOrder(order,availableCars,availableAccessories);
				if(checkedOrder.getErrorMessage()==null || checkedOrder.getErrorMessage().isEmpty()) {
					//placeOrder
				}else {
					invalidOrders.add(checkedOrder);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invalidOrders;
	}
	
	public CarStandingOrders checkForValidOrder(CarStandingOrders order,List<CarInventory> availableCars,List<AccessoryInventory> availableAccessories) {
		List<String> accList = null;
		
		Optional<CarInventory> foundCar = availableCars.stream().filter(car ->{
			return car.matchCarDetails(order);
		}).findAny();
		
		accList = availableAccessories.stream().filter(accessory ->{
			return accessory.matchAccessoryDetails(order);
		}).filter(accessory -> accessory.getQuantity()>0).map(acc -> acc.getAccessories()).collect(Collectors.toList());
		if(!foundCar.isPresent()) {
			order.setErrorMessage("Mismatch with Car Inventory Available");
		}else if(foundCar.get().getQuantity()<=0) {
			order.setErrorMessage("Car Inventory unavailable");
		}else if (accList==null || accList.isEmpty() || !accList.containsAll(order.getAccessories())) {
			order.setErrorMessage("Mismatch with Accesories Inventory Available");
		}
		return order;
	}

	
}
