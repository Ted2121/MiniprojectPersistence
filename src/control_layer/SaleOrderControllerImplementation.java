package control_layer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import control_layer.control_layer_interfaces.LineItemController;
import control_layer.control_layer_interfaces.SaleOrderController;
import data_access_layer.DaoFactory;
import model.Customer;
import model.Invoice;
import model.LineItem;
import model.Product;
import model.SaleOrder;

public class SaleOrderControllerImplementation implements SaleOrderController {
	private LineItemController lineItemController = new LineItemControllerImplementation();

	private Invoice generateInvoice(double amount) {
		//Generated InvoiceNumber can be duplicates, this one only allows 17,576,000,000 possible invoice numbers and a duplicate can appear
		//TODO changing the InvoiceNumber generation
		String Alphabetic = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String invoiceNumber = "";
		
		for (int i = 0; i < 2; i++) {
            invoiceNumber = invoiceNumber + (int)(Math.random()*10);
        }
		
		invoiceNumber = invoiceNumber+"-";
		
        for (int i = 0; i < 3; i++) {
            int index = (int)(Alphabetic.length() * Math.random());
            invoiceNumber = invoiceNumber + Alphabetic.charAt(index);
        }
        
        invoiceNumber = invoiceNumber+"-";
        
        for (int i = 0; i < 4; i++) {
            invoiceNumber = invoiceNumber + (int)(Math.random()*10);
        }
        
        //Setting the paymentDate to the local dateTime
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        
        String paymentDate = dtf.format(now);
        
        //Creating the Invoice
        Invoice invoice = new Invoice(invoiceNumber, paymentDate, amount);
  
		return invoice;
	}
	
	
	@Override
	public String[][] retrieveAllDataAsStringTable(ArrayList<SaleOrder> saleOrders) {
		String[][] stringTypeSaleOrder = new String[saleOrders.size()][5];
		for(int i=0 ; i< saleOrders.size() ; i++) {
			SaleOrder saleOrder = saleOrders.get(i);
			stringTypeSaleOrder[i][0] = saleOrder.getCustomer().getName();
			stringTypeSaleOrder[i][1] = saleOrder.getOrderDate();
			stringTypeSaleOrder[i][2] = saleOrder.getDeliveryDate();
			if(saleOrder.isDeliveryStatus()) {
				stringTypeSaleOrder[i][3] = "delivered";
			}else {
				stringTypeSaleOrder[i][3] = "shipping";
			}
			stringTypeSaleOrder[i][4] = ""+saleOrder.getAmount();
		}
		
		return stringTypeSaleOrder;
	}

	@Override
	public void searchOrder(String search) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SaleOrder addOrder(String customerName, String orderDate, String deliveryDate, String deliveryStatus,
			double amount, ArrayList<Product> products){
		
		//Retrieving all the Products adding them to the product list
		try {
			products.addAll(DaoFactory.getItemDao().findAllItems()); //Hardcoded value for selected products TODO Make product selectable
			products.addAll(DaoFactory.getClothingDao().findAllClothings()); //Hardcoded value for selected products TODO Make product selectable
		} catch (SQLException e1) {
			System.out.println("Cannot retrieve all the items and clothings");
		}
		
		boolean status = false;
		Customer customer = null;
		
		//Finding the customer in the database
		try {
			customer = DaoFactory.getCustomerDao().findCustomerByName(customerName);
		} catch (SQLException e) {
			System.out.println("Cannot find the customer");
		}
		
		//Creating a new invoice
		Invoice invoice = generateInvoice(amount);
		
		//Adding the invoice to the database;
		try {
			addInvoice(invoice);
		} catch (SQLException e1) {
			System.out.println("Cannot create the invoice, maybe because of duplicate invoice number");
		}
		
		//setting the status of delivery
		if(deliveryStatus.equals("delivered"))
			status = true;
		
		//Creating the SaleOrder
		SaleOrder saleOrder = new SaleOrder(orderDate, deliveryDate, status, customer, invoice);
		
		//Adding the SaleOrder to the database
		try {
			DaoFactory.getSaleOrderDao().createSaleOrder(saleOrder);
		} catch (SQLException e) {
			System.out.println("Cannot create the saleOrder");
		}
		
		//Creating all the lineItems and sending them to the lineItemController to insert them
		for(Product product : products) {
			lineItemController.addLineItem(new LineItem(product, saleOrder, 5)); //Hardcoded quantity TODO add a field to insert a quantity
		}
		return saleOrder;
		
	}

	@Override
	public void editOrder(SaleOrder order) {
		try {
			DaoFactory.getSaleOrderDao().updateSaleOrder(order);
		} catch (SQLException e) {
			System.out.println("Couldn't update the order");
		}
	}

	@Override
	public void deleteOrder(SaleOrder order) {
		try {
			lineItemController.deleteSaleOrderLineItems(order);
			DaoFactory.getSaleOrderDao().deleteSaleOrder(order);
			DaoFactory.getSaleOrderDao().setInvoiceRelatedToThisSaleOrder(order);
			DaoFactory.getInvoiceDao().deleteInvoice(order.getInvoice());
		}catch (SQLException e) {
			System.out.println("Couldn't delete the order");
		}
	}

	@Override
	public void displayProductList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInvoice(Invoice invoice) throws SQLException {
		DaoFactory.getInvoiceDao().createInvoice(invoice);
	}
	
	



}
