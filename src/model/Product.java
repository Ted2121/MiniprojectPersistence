package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {
	
	private int id;
	private String name;
	private double purchasePrice;
	private double salePrice;
	private String countryOfOrigin;
	private int minStock;
	private ArrayList<LineItem> lineItem;
	private Supplier supplier;
	private int FK_Supplier;
	private int stock;
	
	public Product(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock, int FK_Supplier) {
		
		this.FK_Supplier = FK_Supplier;
		this.stock = stock;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
	}

	public Product(String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
		ArrayList<LineItem> lineItem, int FK_Supplier) {
		
		this.FK_Supplier = FK_Supplier;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
		this.lineItem = lineItem;
	}
	
	public Product(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock, int FK_Supplier) {
		
		this.FK_Supplier = FK_Supplier;
		this.id = id;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
	}
	
	
	public Product(int id, String name, double purchasePrice, double salePrice, String countryOfOrigin, int minStock, int stock,
			ArrayList<LineItem> lineItem, int FK_Supplier) {
		this.FK_Supplier = FK_Supplier;
		this.id = id;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
		this.lineItem = lineItem;
	}
	
	
	
	
	public ArrayList<LineItem> getSaleOrderProductPair() {
		return lineItem;
	}

	public void setLineItem(ArrayList<LineItem> lineItem) {
		this.lineItem = lineItem;
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
	
	public int getFK_Supplier() {
		return FK_Supplier;
	}

	public void setFK_Supplier(int fK_Supplier) {
		FK_Supplier = fK_Supplier;
	}
	
}
