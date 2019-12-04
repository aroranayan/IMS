package com.accenture.ims.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accenture.ims.model.AccessoryInventory;

@Service
public interface AccessoryInventoryService {
	
	public void saveAccessoriesInfo(List<AccessoryInventory> accessories);
	
}
