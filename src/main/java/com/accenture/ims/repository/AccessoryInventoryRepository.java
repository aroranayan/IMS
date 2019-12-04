package com.accenture.ims.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.ims.model.AccessoryInventory;

@Repository
public interface AccessoryInventoryRepository extends CrudRepository<AccessoryInventory, Long> {
	

}
