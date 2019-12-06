package com.accenture.ims.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.ims.model.AccessoryInventory;
import com.accenture.ims.repository.AccessoryInventoryRepository;

@Service
public class AccessoryInventoryServiceImpl implements AccessoryInventoryService {
	
	@Autowired
	AccessoryInventoryRepository accessRepo;

	/**
	 * Retrieve Accessories List
	 */
	@Override
	@Transactional
	public void saveAccessoriesInfo(List<AccessoryInventory> accessories) {
		try {
			accessRepo.saveAll(accessories);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
