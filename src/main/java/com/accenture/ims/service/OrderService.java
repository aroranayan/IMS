package com.accenture.ims.service;

import java.util.List;

import com.accenture.ims.exceptions.OrderServiceException;
import com.accenture.ims.model.CarStandingOrders;

public interface OrderService {
	
	public List<CarStandingOrders> processOrders(List<CarStandingOrders> orders) throws OrderServiceException;
}
