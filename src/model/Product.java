package model;

import java.util.ArrayList;

public abstract class Product {
	
	private int id;
	private String name;
	private double purchasePrice;
	private double salePrice;
	private String countryOfOrigin;
	private int minStock;
	private ArrayList<SaleOrder> ordersFromProduct;
	
	public Product(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock) {
		
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
	}

	public Product(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock) {
		
		this.id = id;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
	}
	
	public Product(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			ArrayList<SaleOrder> ordersFromProduct) {
		
		this.id = id;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
		this.ordersFromProduct = ordersFromProduct;
	}
	
	public Product(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock,
			ArrayList<SaleOrder> ordersFromProduct) {
		super();
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
		this.ordersFromProduct = ordersFromProduct;
	}
	
	
	public ArrayList<SaleOrder> getOrdersFromProduct() {
		return ordersFromProduct;
	}

	public void setOrdersFromProduct(ArrayList<SaleOrder> orders) {
		this.ordersFromProduct = orders;
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
