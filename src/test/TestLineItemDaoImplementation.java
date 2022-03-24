package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import data_access_layer.DaoFactory;
import data_access_layer.DatabaseConnection;
import data_access_layer.LineItemDaoImplementation;
import model.Product;
import model.SaleOrder;
import model.LineItem;

public class TestLineItemDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static LineItemDaoImplementation lineItemDao = DaoFactory.getLineItemDao();
	static int generatedIdCreateTest;
	static LineItem objectToDelete;
	static LineItem objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTupleToDelete () throws SQLException {
		Product product = DaoFactory.getClothingDao().findClothingById(1); 
		SaleOrder saleOrder = DaoFactory.getSaleOrderDao().findSaleOrderById(3);
		Product product2 = DaoFactory.getClothingDao().findClothingById(2);
		SaleOrder saleOrder2 = DaoFactory.getSaleOrderDao().findSaleOrderById(2); 
		objectToDelete = new LineItem(product,saleOrder, 12 ); 
		objectToUpdate = new LineItem(product2,saleOrder2, 12 ); 
		lineItemDao.createLineItem(objectToDelete);
		lineItemDao.createLineItem(objectToUpdate);
	}
	
	
	@Test
	public void TestLineItemFindBySaleOrder() throws SQLException {
		SaleOrder saleOrder = DaoFactory.getSaleOrderDao().findSaleOrderById(1); 
		List<LineItem> result = lineItemDao.findLineItemsBySaleOrder(saleOrder);
		assertFalse("The retrieved object size should greater than 0", result.isEmpty());
	}
	
	@Test
	public void TestLineItemFindByProduct() throws SQLException {
		Product product = DaoFactory.getClothingDao().findClothingById(1); 
		List<LineItem> result = lineItemDao.findLineItemsByProduct(product);
		assertFalse("The retrieved object size should greater than 0", result.isEmpty());
	}
	
	@Test
	public void TestLineItemById() throws SQLException {
		LineItem result = lineItemDao.findLineItemById(1,1);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestLineItemFindAll() throws SQLException {
		List<LineItem> result = lineItemDao.findAllLineItems();
		assertFalse("The retrieved object size should greater than 0", result.isEmpty());
	}
	
	@Test
	public void TestLineItemInsert() throws SQLException {
		Product product = DaoFactory.getItemDao().findItemById(3); 
		SaleOrder saleOrder = DaoFactory.getSaleOrderDao().findSaleOrderById(1); 
		LineItem testLineItem = new LineItem(product,saleOrder, 12 ); 
		boolean isSucceeded = lineItemDao.createLineItem(testLineItem);
		assertTrue("The retrieved object should be added", isSucceeded);
	}
	
	@Test
	public void TestLineItemDelete() throws SQLException {
		boolean isSucceeded = lineItemDao.deleteLineItem(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestLineItemUpdate() throws SQLException {
		objectToUpdate.setQuantity(100);
		lineItemDao.updateLineItem(objectToUpdate);
		
		assertEquals("Should display 100", 100 , lineItemDao.findLineItemById(objectToUpdate.getOrder().getId(), objectToUpdate.getProduct().getId()).getQuantity());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		LineItem objectToBeCleanUp = lineItemDao.findLineItemById(1,3);
		lineItemDao.deleteLineItem(objectToBeCleanUp);
		lineItemDao.deleteLineItem(objectToUpdate);
	}
}
