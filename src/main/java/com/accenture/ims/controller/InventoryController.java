package com.accenture.ims.controller;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.ims.model.AccessoryInventory;
import com.accenture.ims.model.CarInventory;
import com.accenture.ims.model.CarStandingOrders;
import com.accenture.ims.service.AccessoryInventoryService;
import com.accenture.ims.service.CarInventoryService;
import com.accenture.ims.service.OrderService;
import com.accenture.ims.utils.timelogger.LogExecutionTime;
/**
 * 	Entry point having all the end points
 * 
 *	@author nayan.arora
 */
@RestController
public class InventoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
	
	public static final List<String> CAR_INV_HEADERS = 
		    Collections.unmodifiableList(Arrays.asList("vendor", "model", "variant","color","basePrice","quantityAvailable"));
	
	public static final List<String> ACC_INV_HEADERS = 
		    Collections.unmodifiableList(Arrays.asList("vendor", "model", "accessories","color","price","quantityAvailable"));
	
	public static final List<String> CAR_ORDERS_HEADERS = 
		    Collections.unmodifiableList(Arrays.asList("customerName","region","vendor", "model", "variant","color","accessories","motorInsurance","personalProtectPlan"));
	
	@Autowired
	CarInventoryService carService;
	
	@Autowired
	AccessoryInventoryService accessService;
	
	@Autowired
	OrderService orderService;
	
	/**
	 * To upload cars inventory
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadCars")
    public Object uploadCarsInventory(@RequestParam("file") MultipartFile file) {
		try {
			CSVParser records = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(file.getInputStream()));
			if(!records.getHeaderMap().keySet().containsAll(CAR_INV_HEADERS)){
				throw new Exception("Invalid Columns");
			}
			List<CarInventory> cars = csvToCarsInventory(records.getRecords());
			carService.saveCarDetails(cars);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * To Upload Accessory Inventory
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadAccessories")
    public Object uploadAccessoryInventory(@RequestParam("file") MultipartFile file) {
		try {
			CSVParser records = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(file.getInputStream()));
			records.getHeaderMap().keySet().containsAll(CAR_INV_HEADERS);
			List<AccessoryInventory> accessories = csvToAccessoryInventory(records.getRecords());
			accessService.saveAccessoriesInfo(accessories);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * To convert CSV data to CarInventory Model
	 * @param records
	 * @return List<CarInventory>
	 */
	private List<CarInventory> csvToCarsInventory(List<CSVRecord> records) {
		return records.stream().map(record -> new CarInventory(record.get(0),record.get(1),
    			record.get(2),record.get(3),Integer.valueOf(record.get(4)),Integer.valueOf(record.get(5))))
    			.collect(Collectors.toList());
	}
	/**
	 * To convert CSV data to AccessoryInventory Model
	 * @param records
	 * @return List<AccessoryInventory>
	 */
	private List<AccessoryInventory> csvToAccessoryInventory(List<CSVRecord> records){
    	return records.stream().map(record -> new AccessoryInventory(record.get(0),record.get(1),
    			record.get(2),Integer.valueOf(record.get(3)),Integer.valueOf(record.get(4))))
    			.collect(Collectors.toList());
		
    	
    }
	
	/**
	 * To place orders by uploading a file
	 * Returns a list of invalid orders
	 * @param file
	 * @return List<CarStandingOrders>
	 */
	@PostMapping("/placeOrders")
	@LogExecutionTime
    public List<CarStandingOrders> placeOrders(@RequestParam("file") MultipartFile file) {
		List<CarStandingOrders> invalidOrders = null;
		try {
			CSVParser records = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(file.getInputStream()));
			if(!records.getHeaderMap().keySet().containsAll(CAR_ORDERS_HEADERS)){
				throw new Exception("Invalid Columns");
			}
			List<CarStandingOrders> orders = csvToCarStandingOrders(records.getRecords());
			invalidOrders = orderService.placeOrders(orders);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return invalidOrders;
	}
	
	/**
	 * To convert CSV data to CarStandingOrders model
	 * @param records
	 * @return List<CarStandingOrders>
	 */
	private List<CarStandingOrders> csvToCarStandingOrders(List<CSVRecord> records){
    	return records.stream().map(record -> new CarStandingOrders(record.get(0),record.get(1),
    			record.get(2),record.get(3),record.get(4),record.get(5),Arrays.asList(record.get(6).split(":")),
    			record.get(7),record.get(4).equals("Yes")?true:false))
    			.collect(Collectors.toList());	
    }

}
