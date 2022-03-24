package control_layer;

import java.sql.SQLException;
import java.util.ArrayList;

import control_layer.control_layer_interfaces.LineItemController;
import data_access_layer.DaoFactory;
import model.LineItem;
import model.SaleOrder;

public class LineItemControllerImplementation implements LineItemController{

	@Override
	public void addLineItem(LineItem lineItem) {
		try {
			DaoFactory.getLineItemDao().createLineItem(lineItem);
		} catch (SQLException e) {
			System.out.println("Cannot create the lineItem");
		}
	}

	@Override
	public void deleteSaleOrderLineItems(SaleOrder order) {
		ArrayList<LineItem> lineItems;
		try {
			lineItems = DaoFactory.getLineItemDao().findLineItemsBySaleOrder(order);
			for(LineItem lineItem : lineItems) {
				DaoFactory.getLineItemDao().deleteLineItem(lineItem);
			}
		} catch (SQLException e) {
			System.out.println("Couldn't delete all the lineItems");
		}
	}

	@Override
	public void deleteLineItem(LineItem lineItem) {
		try {
			DaoFactory.getLineItemDao().deleteLineItem(lineItem);
		}catch (SQLException e) {
			System.out.println("Couldn't delete the lineItem");
		}
	}

}
