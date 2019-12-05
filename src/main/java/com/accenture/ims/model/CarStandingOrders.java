package com.accenture.ims.model;

import java.util.List;
import java.util.stream.Collectors;

public class CarStandingOrders {
	
	private String customerName;
	private String region;
	private String vendor;
	private String model;
	private String variant;
	private String color;
	private List<String> accessories;
	private String motorInsurance;
	private Boolean personalProtectPlan;
	private String errorMessage;
	private Double taxExpense;
	private Double salePrice;
	
	public CarStandingOrders() {}
	
	public CarStandingOrders(String customerName, String region, String vendor, String model, String variant,
			String color, List<String> accessories, String motorInsurance, Boolean personalProtectPlan) {
		super();
		this.customerName = customerName;
		this.region = region;
		this.vendor = vendor;
		this.model = model;
		this.variant = variant;
		this.color = color;
		this.accessories = accessories.stream().map(a -> a.trim()).collect(Collectors.toList());
		this.motorInsurance = motorInsurance;
		this.personalProtectPlan = personalProtectPlan;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getAccessories() {
		return accessories;
	}

	public void setAccessories(List<String> accessories) {
		this.accessories = accessories;
	}

	public String getMotorInsurance() {
		return motorInsurance;
	}

	public void setMotorInsurance(String motorInsurance) {
		this.motorInsurance = motorInsurance;
	}

	public Boolean getPersonalProtectPlan() {
		return personalProtectPlan;
	}

	public void setPersonalProtectPlan(Boolean personalProtectPlan) {
		this.personalProtectPlan = personalProtectPlan;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Double getTaxExpense() {
		return taxExpense;
	}

	public void setTaxExpense(Double taxExpense) {
		this.taxExpense = taxExpense;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
}
