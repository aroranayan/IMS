package com.accenture.ims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class to save Accessory Details
 * @author nayan.arora
 *
 */

@Entity
public class AccessoryInventory {
	
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String vendor;
	@Column
	private String model;
	@Column
	private String accessories;
	@Column
	private Integer price;
	@Column
	private Integer quantity;
	
	public AccessoryInventory() {}
	
	public AccessoryInventory(String vendor, String model, String accessories, Integer price,
			Integer quantity) {
		super();
		this.vendor = vendor;
		this.model = model;
		this.accessories = accessories.trim();
		this.price = price;
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
	public String getAccessories() {
		return accessories;
	}
	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Boolean matchAccessoryDetails(CarStandingOrders order) {
		return (this.model.equals(order.getModel())
				&& this.vendor.equals(order.getVendor()));
	}

}
