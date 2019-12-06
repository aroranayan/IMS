package com.accenture.ims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class to save Car Details
 * @author nayan.arora
 *
 */
@Entity
public class CarInventory {
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String vendor;
	@Column
	private String model;
	@Column
	private String variant;
	@Column
	private String color;
	@Column
	private Integer basePrice;
	@Column
	private Integer quantity;
	
	public CarInventory() {}
	
	public CarInventory(String vendor, String model, String variant, String color, Integer basePrice,
			Integer quantity) {
		super();
		this.vendor = vendor;
		this.model = model;
		this.variant = variant;
		this.color = color;
		this.basePrice = basePrice;
		this.quantity = quantity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Integer getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Integer basePrice) {
		this.basePrice = basePrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Boolean matchCarDetails(CarStandingOrders order) {
		return (this.color.equals(order.getColor()) && this.model.equals(order.getModel()) && this.variant.equals(order.getVariant())
				&& this.vendor.equals(order.getVendor()));
	}
}
