package com.accenture.ims.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.ims.model.InsuranceProvider;

@Repository
public interface InsuranceProviderRepository extends CrudRepository<InsuranceProvider, String> {
 
}
