package com.accenture.ims.service;

import java.util.List;

import com.accenture.ims.model.CarStandingOrders;

public interface OrderService {
	
	public List<CarStandingOrders> placeOrders(List<CarStandingOrders> orders);
}
