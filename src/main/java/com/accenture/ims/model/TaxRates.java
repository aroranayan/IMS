package com.accenture.ims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TaxRates {
	
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String state;
	@Column
	private Double taxRate;
	
	public TaxRates(String state, Double taxRate) {
		super();
		this.state = state;
		this.taxRate = taxRate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
	
	
	

}
