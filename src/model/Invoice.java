package model;

import java.time.LocalDateTime;
import java.util.Random;

public class Invoice {
	private int id;
	private String invoiceNo;
	private String paymentDate;
	private double amount;
	
	public Invoice(int id, String invoiceNo, String paymentDate, double amount) {
		
		this.id = id;
		this.invoiceNo = invoiceNo;
		this.paymentDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.amount = amount;
	}

	public Invoice(String invoiceNo, String paymentDate, double amount) {
		
		this.invoiceNo = invoiceNo;
		this.paymentDate = LocalDateTime.now().format(HelperModelClass.getFormat());
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	
}
