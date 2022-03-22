package model;

import java.util.ArrayList;

public class Item extends Product{
	private String type;
	private String description;
	

	


	public Item(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
			Supplier supplier, String type, String description) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, supplier);
		this.type = type;
		this.description = description;
	}
	
	public Item(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
			Supplier supplier, String type, String description) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, supplier);
		this.type = type;
		this.description = description;
	}
	
	public Item(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
			Supplier supplier, String type, String description, SaleOrder_Product saleOrderProductPair) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, supplier, saleOrderProductPair);
		this.type = type;
		this.description = description;
	}
	
	public Item(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
			Supplier supplier, String type, String description, SaleOrder_Product saleOrderProductPair) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, supplier, saleOrderProductPair);
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
