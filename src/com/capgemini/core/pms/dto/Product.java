package com.capgemini.core.pms.dto;

import java.util.Date;

//Bean
public class Product {

	private int id;
	private String name;
	private double price;
	private String category;
	private Date ManufactureDate;
	
	public Product(){
		super();
	}
	public Product(int id, String name, double price, String category,Date manufactureDate) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		ManufactureDate = manufactureDate;
	}
	public Product(String name, double price, String category,Date manufactureDate) {
		this.name = name;
		this.price = price;
		this.category = category;
		ManufactureDate = manufactureDate;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", category=" + category + ", ManufactureDate="
				+ ManufactureDate + "]\n";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getManufactureDate() {
		return ManufactureDate;
	}
	public void setManufactureDate(Date manufactureDate) {
		ManufactureDate = manufactureDate;
	}
	
	
}
