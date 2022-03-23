package model;

import java.util.List;

public class Clothing extends Product{
	
	private String size;
	private String color;
	


	public Clothing(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			int stock, String size, String color, int FK_Supplier) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, FK_Supplier);
		this.size = size;
		this.color = color;
	}
	
	public Clothing(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			int stock, String size, String color, int FK_Supplier) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, FK_Supplier);
		this.size = size;
		this.color = color;
	}
	
	public Clothing(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			int stock, String size, String color, List<LineItem> saleOrderProductPair, int FK_Supplier) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, saleOrderProductPair, FK_Supplier);
		this.size = size;
		this.color = color;
	}
	
	public Clothing(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			int stock, String size, String color, List<LineItem> saleOrderProductPair, int FK_Supplier) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, saleOrderProductPair, FK_Supplier);
		this.size = size;
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void setSupplier(int FK_Supplier) {
		super.setFK_Supplier(FK_Supplier);
	}
	
	
	
}
