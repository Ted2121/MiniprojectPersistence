package model;

import java.util.ArrayList;

public abstract class Product {
	
	private int id;
	private String name;
	private double purchasePrice;
	private double salePrice;
	private String countryOfOrigin;
	private int minStock;
	private SaleOrder_Product saleOrderProductPair;
	private Supplier supplier;
	private int stock;
	
	public Product(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock) {
		
	
		this.stock = stock;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
	}

	public Product(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
			SaleOrder_Product saleOrderProductPair) {
		
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
		this.saleOrderProductPair = saleOrderProductPair;
	}
	
	public Product(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock) {
		
		this.id = id;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
	}
	
	
	public Product(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
			SaleOrder_Product saleOrderProductPair) {
		
		this.id = id;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
		this.saleOrderProductPair = saleOrderProductPair;
	}
	
	
	
	
	public SaleOrder_Product getSaleOrderProductPair() {
		return saleOrderProductPair;
	}

	public void setSaleOrderProductPair(SaleOrder_Product saleOrderProductPair) {
		this.saleOrderProductPair = saleOrderProductPair;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	
}
