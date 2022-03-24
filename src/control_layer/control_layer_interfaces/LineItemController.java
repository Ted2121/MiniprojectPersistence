package control_layer.control_layer_interfaces;


import model.LineItem;
import model.Product;
import model.SaleOrder;

public interface LineItemController {

	void addLineItem(LineItem lineItem);
	void deleteLineItem(LineItem lineItem);
	void deleteSaleOrderLineItems(SaleOrder order);
}
