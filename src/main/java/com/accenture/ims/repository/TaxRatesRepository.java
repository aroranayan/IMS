package com.accenture.ims.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.ims.model.TaxRates;

@Repository
public interface TaxRatesRepository extends CrudRepository<TaxRates, Long> {
 
}
