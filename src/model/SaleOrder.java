package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SaleOrder {
	
	private int id;
	private String orderDate;
	private String deliveryDate;
	private boolean deliveryStatus;
	private Customer customer;
	private Invoice invoice;
	private double amount;
	private List<SaleOrder_Product> saleOrderProductPair;
	private int FK_Invoice;
	private int FK_Customer;
	
	public SaleOrder(String orderDate, String deliveryDate, boolean deliveryStatus, Customer customer,
			Invoice invoice) {
	
		this.orderDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(HelperModelClass.getFormat());
		this.deliveryStatus = deliveryStatus;
		this.customer = customer;
		this.invoice = invoice;
		this.FK_Invoice = invoice.getId();
		this.FK_Customer = customer.getId();
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
    
    public SaleOrder(int id, String orderDate, String deliveryDate, boolean deliveryStatus) {
		
    	this.id = id;
    	this.orderDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(HelperModelClass.getFormat());
		this.deliveryStatus = deliveryStatus;
	}
    
    public SaleOrder(int id, String orderDate, String deliveryDate, boolean deliveryStatus, double amount) {
		
    	this.id = id;
    	this.amount = amount;
    	this.orderDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(HelperModelClass.getFormat());
		this.deliveryStatus = deliveryStatus;
	}



	public SaleOrder(int id, String orderDate, String deliveryDate, boolean deliveryStatus, Customer customer,
			Invoice invoice, List<SaleOrder_Product> saleOrderProductPair) {
		
		this.id = id;
		this.orderDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(HelperModelClass.getFormat());
		this.deliveryStatus = deliveryStatus;
		this.customer = customer;
		this.invoice = invoice;
		this.saleOrderProductPair = saleOrderProductPair;
	}


	public SaleOrder(String orderDate, String deliveryDate, boolean deliveryStatus, Customer customer, Invoice invoice, List<SaleOrder_Product> saleOrderProductPair) {
		
		this.orderDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(HelperModelClass.getFormat());
		this.deliveryStatus = deliveryStatus;
		this.customer = customer;
		this.invoice = invoice;
		this.saleOrderProductPair = saleOrderProductPair;
	}

	
	
	public double getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public List<SaleOrder_Product> getSaleOrderProductPair() {
		return saleOrderProductPair;
	}

	public void setSaleOrderProductPair(List<SaleOrder_Product> saleOrderProductPair) {
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

	public int getFK_Invoice() {
		return FK_Invoice;
	}

	public void setFK_Invoice(int fK_Invoice) {
		FK_Invoice = fK_Invoice;
	}
	
	public int getFK_Customer() {
		return FK_Customer;
	}
	
	public void setFK_Customer(int fK_Customer) {
		FK_Customer = fK_Customer;
	}
    
    
}
