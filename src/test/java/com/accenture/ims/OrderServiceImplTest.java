package com.accenture.ims;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.accenture.ims.exceptions.OrderServiceException;
import com.accenture.ims.model.AccessoryInventory;
import com.accenture.ims.model.CarInventory;
import com.accenture.ims.model.CarStandingOrders;
import com.accenture.ims.model.InsuranceProvider;
import com.accenture.ims.model.TaxRates;
import com.accenture.ims.repository.AccessoryInventoryRepository;
import com.accenture.ims.repository.CarInventoryRepository;
import com.accenture.ims.repository.InsuranceProviderRepository;
import com.accenture.ims.repository.SalesEstimateRepository;
import com.accenture.ims.repository.TaxRatesRepository;
import com.accenture.ims.service.OrderServiceImpl;

/**
 * @author nayan.arora
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
	
	
	@Mock
	SalesEstimateRepository salesRepo;
	
	@Mock
	CarInventoryRepository carRepo;
	
	@Mock
	AccessoryInventoryRepository accessRepo;
	
	@Mock
	TaxRatesRepository taxRepo;
	
	@Mock
	InsuranceProviderRepository insuranceRepo;
	
	
	@InjectMocks
	OrderServiceImpl orderService;

	/**
	 * Test method for {@link com.accenture.ims.service.OrderServiceImpl#processOrders(java.util.List)}.
	 * @throws OrderServiceException 
	 */
	@Test
	public void testProcessOrders() throws OrderServiceException {
		List<CarStandingOrders> orders = new ArrayList<CarStandingOrders>();
		CarStandingOrders order = new CarStandingOrders("Abey", "Karnataka", "Tata", "Nano", "Petrol", "White", new ArrayList<>(Arrays. asList("Seat Cover", "AC")), "Bajaj", false);
		CarStandingOrders order2 = new CarStandingOrders("Omi", "Karnataka", "Tata", "Nano", "Petrol", "Black", new ArrayList<>(Arrays. asList("AC")), "ICICI", true);
		CarStandingOrders order3 = new CarStandingOrders("Karl", "Karnataka", "Tata", "Nano", "Petrol", "Black", new ArrayList<>(Arrays. asList("Seat Cover", "AC")), "Bajaj", false);
		orders.add(order);
		orders.add(order2);
		orders.add(order3);
		List<CarInventory> cars = new ArrayList<CarInventory>();
		CarInventory car = new CarInventory("Tata", "Nano", "Petrol", "White", 1500, 20);
		CarInventory car2 = new CarInventory("Tata", "Nano", "Petrol", "Black", 1500, 20);
		CarInventory car3 = new CarInventory("Tata", "Nano", "Petrol", "Black", 1500, 20);
		cars.add(car);
		cars.add(car2);
		cars.add(car3);
		List<AccessoryInventory> accessories = new ArrayList<AccessoryInventory>();
		AccessoryInventory accessory = new AccessoryInventory("Tata", "Nano", "Seat Cover", 100, 10);
		AccessoryInventory accessory2 = new AccessoryInventory("Tata", "Nano", "AC", 100, 10);
		AccessoryInventory accessory3 = new AccessoryInventory("Tata", "Nano", "Door", 100, 10);
		accessories.add(accessory);
		accessories.add(accessory2);
		accessories.add(accessory3);
		
		List<TaxRates> taxes = new ArrayList<TaxRates>();
		TaxRates tax = new TaxRates("Karnataka", 15.0);
		taxes.add(tax);
		
		List<InsuranceProvider> insuranceProviders = new ArrayList<InsuranceProvider>();
		InsuranceProvider provider = new InsuranceProvider("ICICI", "No", 1500);
		InsuranceProvider provider2 = new InsuranceProvider("Bajaj", "Yes", 1500);
		insuranceProviders.add(provider);
		insuranceProviders.add(provider2);
		
		when(carRepo.findAll()).thenReturn(cars);
		when(accessRepo.findAll()).thenReturn(accessories);
		when(taxRepo.findAll()).thenReturn(taxes);
		when(insuranceRepo.findAll()).thenReturn(insuranceProviders);
		
		assertEquals(0, orderService.processOrders(orders).size());
		
		CarStandingOrders order4 = new CarStandingOrders("Karl", "Karnataka", "Tata", "Nano", "Petrol", "Blue", new ArrayList<>(Arrays. asList("Seat Cover", "AC")), "Bajaj", false);
		orders.add(order4);
		
		assertNotNull(orderService.processOrders(orders));
		assertEquals(1, orderService.processOrders(orders).size());
		
	}
}
