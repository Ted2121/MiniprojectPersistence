package model;

import java.util.List;

public class Clothing extends Product{
	
	private String size;
	private String color;
	


	public Clothing(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			int stock, String size, String color) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, stock);
		this.size = size;
		this.color = color;
	}
	
	public Clothing(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			int stock, String size, String color) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock, stock);
		this.size = size;
		this.color = color;
	}
	
	public Clothing(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			int stock, String size, String color, List<LineItem> saleOrderProductPair) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, saleOrderProductPair);
		this.size = size;
		this.color = color;
	}
	
	public Clothing(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			int stock, String size, String color, List<LineItem> saleOrderProductPair) {
		super(id, name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, saleOrderProductPair);
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
