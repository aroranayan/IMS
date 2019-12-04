package com.accenture.ims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SalesEstimate {
		
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String region;
	@Column
	private Integer unitsSold;
	@Column
	private Double totalSales;
	@Column
	private Double netIncome;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(Integer unitsSold) {
		this.unitsSold = unitsSold;
	}
	public Double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}
	public Double getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}
	
	
}
