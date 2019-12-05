/**
 * 
 */
package com.accenture.ims;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.accenture.ims.exceptions.OrderServiceException;
import com.accenture.ims.model.AccessoryInventory;
import com.accenture.ims.repository.AccessoryInventoryRepository;
import com.accenture.ims.repository.CarInventoryRepository;
import com.accenture.ims.repository.InsuranceProviderRepository;
import com.accenture.ims.repository.SalesEstimateRepository;
import com.accenture.ims.repository.TaxRatesRepository;
import com.accenture.ims.service.OrderService;

/**
 * @author nayan.arora
 *
 */
@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {
	
	
	@InjectMocks
	OrderService orderService;
	
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
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	/**
	 * Test method for {@link com.accenture.ims.service.OrderServiceImpl#processOrders(java.util.List)}.
	 * @throws OrderServiceException 
	 */
	@Test
	void testProcessOrders() throws OrderServiceException {
		when(carRepo.findAll()).thenReturn(null);
		when(accessRepo.findAll()).thenReturn(null);
		when(taxRepo.findAll()).thenReturn(null);
		when(insuranceRepo.findAll()).thenReturn(null);
		
		assertNotNull(orderService.processOrders(null));
		
	}

//	/**
//	 * Test method for {@link com.accenture.ims.service.OrderServiceImpl#checkForValidOrder(com.accenture.ims.model.CarStandingOrders, java.util.List, java.util.List, java.util.List, java.util.List)}.
//	 */
//	@Test
//	void testCheckForValidOrder() {
//		fail("Not yet implemented");
//	}

//	/**
//	 * Test method for {@link com.accenture.ims.service.OrderServiceImpl#placeOrder(com.accenture.ims.model.CarStandingOrders)}.
//	 */
//	@Test
//	void testPlaceOrder() {
//		SalesEstimate saleForState = null;
//		CarStandingOrders order = new CarStandingOrders();
//		when(salesRepo.findByRegion("")).thenReturn(saleForState);
//		
//		when(salesRepo.save(saleForState)).thenReturn(saleForState);
//		
//		doCallRealMethod().when(orderServiceImpl).placeOrder(order);
//	}
//
//	/**
//	 * Test method for {@link com.accenture.ims.service.OrderServiceImpl#getAccessoryCost(java.util.List, java.util.Map)}.
//	 */
//	@Test
//	void testGetAccessoryCost() {
//		Double cost = 0.0;
//		List<String> accessories = new ArrayList<String>();
//		accessories.add("AC");
//		
//		Map<String, AccessoryInventory> accMap = new HashMap<String, AccessoryInventory>();
//		AccessoryInventory accessInventory = new AccessoryInventory();
//		accessInventory.setAccessories("AC");
//		accessInventory.setQuantity(1);
//		accessInventory.setPrice(10);
//		accMap.put(accessInventory.getAccessories(), accessInventory);
//		
//		cost = orderServiceImpl.getAccessoryCost(accessories, accMap);
//		assertNotNull(cost);
//		
//		accessories.add("Seat Cover");
//		cost = orderServiceImpl.getAccessoryCost(accessories, accMap);
//		assertTrue(cost == null);
//		
//	}

}
