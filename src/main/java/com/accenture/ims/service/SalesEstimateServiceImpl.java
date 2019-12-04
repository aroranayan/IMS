package com.accenture.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.ims.model.SalesEstimate;
import com.accenture.ims.repository.SalesEstimateRepository;

@Service
public class SalesEstimateServiceImpl implements SalesEstimateService{
	
	@Autowired
	SalesEstimateRepository salesRepo;

	@Override
	public List<SalesEstimate> getSalesEstimates() {
		return (List<SalesEstimate>) salesRepo.findAll();
	}

}
