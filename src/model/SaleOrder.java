package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleOrder {
	private int id;
	private String orderDate;
	private String deliveryDate;
	private boolean deliveryStatus;
	private Customer customer;
	private Invoice invoice;
	
	private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	
	
	public SaleOrder(String orderDate, String deliveryDate, boolean deliveryStatus, Customer customer,
			Invoice invoice) {
	
		this.orderDate = LocalDateTime.now().format(getFormat());
		this.deliveryDate = LocalDateTime.now().plusDays(14).format(getFormat());
		this.deliveryStatus = deliveryStatus;
		this.customer = customer;
		this.invoice = invoice;
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


	public static DateTimeFormatter getFormat() {
        return format;
    }
    
    
}
