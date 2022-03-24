package control_layer.control_layer_interfaces;

import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.SaleOrder;

public interface SaleOrderController {
	void searchOrder(String search);
	void addOrder(String customerName, String orderDate, String deliveryDate, String deliveryStatus, double amount, ArrayList<Product> product);
	void editOrder(SaleOrder order);
	void deleteOrder(SaleOrder order);
	void productList();
}
