package model;

import java.util.ArrayList;

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
			int stock, String size, String color, SaleOrder_Product saleOrderProductPair) {
		super(name, purchasePrice, salePrice, countryOfOrigin, minStock, stock, saleOrderProductPair);
		this.size = size;
		this.color = color;
	}
	
	public Clothing(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			int stock, String size, String color, SaleOrder_Product saleOrderProductPair) {
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
	
	
	
}
