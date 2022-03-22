package model;

import java.util.ArrayList;

public class SaleOrder_Product {
	private ArrayList <Product> products;
	private ArrayList <SaleOrder> saleOrders;
	private int quantity;
	private Product product;
	private SaleOrder order;
	
	public SaleOrder_Product(ArrayList<Product> products, ArrayList<SaleOrder> saleOrders, int quantity,
			Product product, SaleOrder order) {
		
		this.products = products;
		this.saleOrders = saleOrders;
		this.quantity = quantity;
		this.product = product;
		this.order = order;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<SaleOrder> getSaleOrders() {
		return saleOrders;
	}

	public void setSaleOrders(ArrayList<SaleOrder> saleOrders) {
		this.saleOrders = saleOrders;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SaleOrder getOrder() {
		return order;
	}

	public void setOrder(SaleOrder order) {
		this.order = order;
	}
	
	
	
	
}
