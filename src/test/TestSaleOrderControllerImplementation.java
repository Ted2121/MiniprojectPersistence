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

public class TestSaleOrderControllerImplementation {
static SaleOrderController controller = new SaleOrderControllerImplementation();
	
	static SaleOrder saleOrder;
	static SaleOrder insertedSaleOrder;
	
	
	@Test
	public void TestRetrieveAllDataAsStringTable() throws SQLException {
		
		ArrayList<SaleOrder> saleOrders = DaoFactory.getSaleOrderDao().findAllSaleOrders();
		
		for(SaleOrder saleOrder : saleOrders) {
			DaoFactory.getSaleOrderDao().setCustomerRelatedToThisSaleOrder(saleOrder);
		}
		
		String[][] test = controller.retrieveAllDataAsStringTable(saleOrders);
		assertTrue("Should be an String array with 4 lines", test.length >= 4);
	}
	
	@Test
	public void TestInsertSaleOrder() throws SQLException {
		int numberOfTuplesBeforeInsert = DaoFactory.getSaleOrderDao().findAllSaleOrders().size();
		insertedSaleOrder = controller.addOrder("Vicent Delavigne", "2022-03-24 18:32:00:000" , "2022-03-29 18:32:00:000", "delivered", 156, new ArrayList<Product>());
		assertTrue("Should be an String array with 4 lines", DaoFactory.getSaleOrderDao().findAllSaleOrders().size() > numberOfTuplesBeforeInsert);
	}
	
	@Test
	public void TestUpdateSaleOrder() throws SQLException {
		saleOrder = controller.addOrder("Vicent Delavigne", "2022-03-24 18:32:00:000" , "2022-03-29 18:32:00:000", "delivered", 156, new ArrayList<Product>());
		SaleOrder saleOrderUpdated = DaoFactory.getSaleOrderDao().findSaleOrderByInvoice(saleOrder.getInvoice());
		DaoFactory.getSaleOrderDao().setInvoiceRelatedToThisSaleOrder(saleOrderUpdated);
		DaoFactory.getSaleOrderDao().setCustomerRelatedToThisSaleOrder(saleOrderUpdated);
		saleOrderUpdated.setDeliveryStatus(false);
		controller.editOrder(saleOrderUpdated);
		assertNotEquals("Should be an differents", saleOrder, DaoFactory.getSaleOrderDao().findSaleOrderByInvoice(saleOrder.getInvoice()));
	}
	
	@Test
	public void TestDeleteSaleOrder() throws SQLException {
		SaleOrder toDeleteSaleOrder = controller.addOrder("Vicent Delavigne", "2022-03-24 18:32:00:000" , "2022-03-29 18:32:00:000", "delivered", 156, new ArrayList<Product>());
		assertNotNull("Should retrieve a SaleOrder", DaoFactory.getSaleOrderDao().findSaleOrderByInvoice(toDeleteSaleOrder.getInvoice()));
		controller.deleteOrder(toDeleteSaleOrder);
		SaleOrder retrievedSaleOrder = DaoFactory.getSaleOrderDao().findSaleOrderByInvoice(toDeleteSaleOrder.getInvoice());
		assertNull("Should not retrieve the SaleOrder", retrievedSaleOrder);
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException{
		controller.deleteOrder(saleOrder);
		controller.deleteOrder(insertedSaleOrder);
		
	}
	
	
}
