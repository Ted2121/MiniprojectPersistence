package control_layer.control_layer_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Invoice;
import model.LineItem;
import model.Product;
import model.SaleOrder;

public interface SaleOrderController {
	String[][] retrieveAllDataAsStringTable(ArrayList<SaleOrder> saleOrders);
	void searchOrder(String search);
	SaleOrder addOrder(String customerName, String orderDate, String deliveryDate, String deliveryStatus, double amount, ArrayList<Product> product);
	void addInvoice(Invoice invoice) throws SQLException;
	void editOrder(SaleOrder order);
	void deleteOrder(SaleOrder order);
	void displayProductList();
}
