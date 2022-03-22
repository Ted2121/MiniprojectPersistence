package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SaleOrder {
	
	private int id;
	private String orderDate;
	private String deliveryDate;
	private boolean deliveryStatus;
	private Customer customer;
	private Invoice invoice;
	private int amount;
	private SaleOrder_Product saleOrderProductPair;
	

	
	
	public SaleOrder(String orderDate, String deliveryDate, boolean deliveryStatus, Customer customer,
			Invoice invoice) {
	
		this.orderDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(HelperModelClass.getFormat());
		this.deliveryStatus = deliveryStatus;
		this.customer = customer;
		this.invoice = invoice;
	}
	
	

	

    public SaleOrder(int id, String orderDate, String deliveryDate, boolean deliveryStatus, Customer customer,
			Invoice invoice) {
		
    	this.id = id;
    	this.orderDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(HelperModelClass.getFormat());
		this.deliveryStatus = deliveryStatus;
		this.customer = customer;
		this.invoice = invoice;
	}



	public SaleOrder(int id, String orderDate, String deliveryDate, boolean deliveryStatus, Customer customer,
			Invoice invoice, SaleOrder_Product saleOrderProductPair) {
		
		this.id = id;
		this.orderDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(HelperModelClass.getFormat());
		this.deliveryStatus = deliveryStatus;
		this.customer = customer;
		this.invoice = invoice;
		this.saleOrderProductPair = saleOrderProductPair;
	}


	public SaleOrder(String orderDate, String deliveryDate, boolean deliveryStatus, Customer customer, Invoice invoice, SaleOrder_Product saleOrderProductPair) {
		
		this.orderDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(HelperModelClass.getFormat());
		this.deliveryStatus = deliveryStatus;
		this.customer = customer;
		this.invoice = invoice;
		this.saleOrderProductPair = saleOrderProductPair;
	}

	
	
	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public SaleOrder_Product getSaleOrderProductPair() {
		return saleOrderProductPair;
	}

	public void setSaleOrderProductPair(SaleOrder_Product saleOrderProductPair) {
		this.saleOrderProductPair = saleOrderProductPair;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public String getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}


	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Invoice getInvoice() {
		return invoice;
	}


	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}


    
    
}
