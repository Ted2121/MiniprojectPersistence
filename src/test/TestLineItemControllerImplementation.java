package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import control_layer.LineItemControllerImplementation;
import control_layer.SaleOrderControllerImplementation;
import control_layer.control_layer_interfaces.LineItemController;
import control_layer.control_layer_interfaces.SaleOrderController;
import data_access_layer.ClothingDaoImplementation;
import data_access_layer.DaoFactory;
import data_access_layer.DatabaseConnection;
import model.Clothing;
import model.LineItem;
import model.Product;
import model.SaleOrder;
import model.Supplier;

public class TestLineItemControllerImplementation {
	
	
	static LineItemController lineItemController = new LineItemControllerImplementation();
	static SaleOrderController saleOrderController = new SaleOrderControllerImplementation();
	
	static LineItem lineItem;
	static LineItem insertedLineItem;
	
	@Test
	public void TestInsertLineItem() throws SQLException {
		int numberOfTuplesBeforeInsert = DaoFactory.getLineItemDao().findAllLineItems().size();
		lineItem = new LineItem(DaoFactory.getItemDao().findItemById(3),DaoFactory.getSaleOrderDao().findSaleOrderById(3),5);
		lineItemController.addLineItem(lineItem);
		assertTrue("Should have more than " + numberOfTuplesBeforeInsert + " tuples", DaoFactory.getLineItemDao().findAllLineItems().size() > numberOfTuplesBeforeInsert);
	}
	
	@Test
	public void TestDeleteLineItemWithSaleOrder() throws SQLException {
		SaleOrder saleOrder = saleOrderController.addOrder("Vicent Delavigne", "2022-03-24 18:32:00:000" , "2022-03-29 18:32:00:000", "delivered", 156, new ArrayList<Product>());
		
		assertFalse("Should retrieve lineItems", DaoFactory.getLineItemDao().findLineItemsBySaleOrder(saleOrder).isEmpty());
		saleOrderController.deleteOrder(saleOrder);
		SaleOrder retrievedSaleOrder = DaoFactory.getSaleOrderDao().findSaleOrderByInvoice(saleOrder.getInvoice());
		assertNull("Should not retrieve the SaleOrder", retrievedSaleOrder);
		assertTrue("Should not retrieve itemLines", DaoFactory.getLineItemDao().findLineItemsBySaleOrder(saleOrder).isEmpty());
	}
	
	@Test
	public void TestDeleteLineItemWithoutSaleOrder() throws SQLException {
		LineItem lineItem = new LineItem(DaoFactory.getClothingDao().findClothingById(1), DaoFactory.getSaleOrderDao().findSaleOrderById(3), 3);
		lineItemController.addLineItem(lineItem);
		assertNotNull("Should retrieve the lineItem", DaoFactory.getLineItemDao().findLineItemById(lineItem.getOrder().getId(), lineItem.getProduct().getId()));
		lineItemController.deleteLineItem(lineItem);
		assertNull("Shouldn't retrieve the lineItem", DaoFactory.getLineItemDao().findLineItemById(lineItem.getOrder().getId(), lineItem.getProduct().getId()));
		
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException{
		lineItemController.deleteLineItem(lineItem);
	}
	
	
}
