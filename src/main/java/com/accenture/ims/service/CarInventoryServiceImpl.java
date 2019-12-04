package com.accenture.ims.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.ims.model.CarInventory;
import com.accenture.ims.repository.CarInventoryRepository;

@Service
public class CarInventoryServiceImpl implements CarInventoryService {
	
	@Autowired
	CarInventoryRepository carRepo;

	@Override
	@Transactional
	public void saveCarDetails(List<CarInventory> cars) {
		try {
			carRepo.saveAll(cars);
			//carRepo.findAll().forEach(car -> System.out.println(car.getModel()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
