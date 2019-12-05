package com.accenture.ims.repository;

import org.springframework.stereotype.Repository;

import com.accenture.ims.model.CarInventory;
import com.accenture.ims.model.SalesEstimate;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface SalesEstimateRepository extends CrudRepository<SalesEstimate, Long> {
	
	public SalesEstimate findByRegion(String region);

}
