package model;

import java.util.ArrayList;
import java.util.List;

public class Item extends Product{
	private String type;
	private String description;
	

	public Item(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock, String type, String description, int FK_Supplier) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, FK_Supplier);
		this.type = type;
		this.description = description;
	}
	
	public Item(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
		 String type, String description, int FK_Supplier) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, FK_Supplier);
		this.type = type;
		this.description = description;
	}
	
	public Item(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
		 String type, String description, ArrayList<LineItem> saleOrderProductPair, int FK_Supplier) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, saleOrderProductPair, FK_Supplier);
		this.type = type;
		this.description = description;
	}
	
	public Item(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock, String type, 
			String description, ArrayList<LineItem> saleOrderProductPair, int FK_Supplier) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, saleOrderProductPair, FK_Supplier);
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
	
	public void setFK_Supplier(int FK_Supplier) {
		super.setFK_Supplier(FK_Supplier);
	}
	
	
	
}
