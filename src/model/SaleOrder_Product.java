package model;

import java.util.ArrayList;

public class SaleOrder_Product {
	private int quantity;
	private Product product;
	private SaleOrder order;
	
	public SaleOrder_Product(Product product, SaleOrder order, int quantity) {
		
		this.quantity = quantity;
		this.product = product;
		this.order = order;
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
