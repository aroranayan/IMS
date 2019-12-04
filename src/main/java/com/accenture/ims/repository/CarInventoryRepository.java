package com.accenture.ims.repository;

import org.springframework.stereotype.Repository;

import com.accenture.ims.model.CarInventory;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface CarInventoryRepository extends CrudRepository<CarInventory, Long> {	

}
