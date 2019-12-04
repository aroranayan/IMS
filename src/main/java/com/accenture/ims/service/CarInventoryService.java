package com.accenture.ims.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accenture.ims.model.CarInventory;

@Service
public interface CarInventoryService {
	
	public void saveCarDetails(List<CarInventory> cars);
	
}
