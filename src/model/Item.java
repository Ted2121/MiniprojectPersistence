package model;

import java.util.ArrayList;

public class Item extends Product{
	private String type;
	private String description;
	
	public Item(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, String type,
			String description) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock);
		this.type = type;
		this.description = description;
	}
	
	public Item(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, String type,
			String description) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock);
		this.type = type;
		this.description = description;
	}
	
	

	public Item(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, String type,
			String description, ArrayList<SaleOrder> ordersFromProduct) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, ordersFromProduct);
		this.type = type;
		this.description = description;
	}
	
	public Item(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, String type,
			String description, ArrayList<SaleOrder> ordersFromProduct) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock, ordersFromProduct);
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
