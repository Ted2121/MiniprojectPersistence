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

import data_access.LineItemDaoImplementation;
import data_access.DaoFactory;
import data_access.DatabaseConnection;
import model.Product;
import model.SaleOrder;
import model.LineItem;

public class TestLineItemDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static LineItemDaoImplementation LineItemDao = DaoFactory.getLineItemDao();
	static int generatedIdCreateTest;
	static LineItem objectToDelete;
	static LineItem objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		Product product = DaoFactory.getClothingDao().findById(1); 
		SaleOrder saleOrder = DaoFactory.getSaleOrderDao().findById(3);
		Product product2 = DaoFactory.getClothingDao().findById(2);
		SaleOrder saleOrder2 = DaoFactory.getSaleOrderDao().findById(2); 
		objectToDelete = new LineItem(product,saleOrder, 12 ); 
		objectToUpdate = new LineItem(product2,saleOrder2, 12 ); 
		LineItemDao.create(objectToDelete);
		LineItemDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestLineItemFindBySaleOrder() throws SQLException {
		SaleOrder saleOrder = DaoFactory.getSaleOrderDao().findById(1); 
		List<LineItem> result = LineItemDao.findBySaleOrder(saleOrder);
		assertFalse("The retrieved object size should greater than 0", result.isEmpty());
	}
	
	@Test
	public void TestLineItemFindByProduct() throws SQLException {
		Product product = DaoFactory.getClothingDao().findById(1); 
		List<LineItem> result = LineItemDao.findByProduct(product);
		assertFalse("The retrieved object size should greater than 0", result.isEmpty());
	}
	
	@Test
	public void TestLineItemById() throws SQLException {
		LineItem result = LineItemDao.findById(1,1);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestLineItemFindAll() throws SQLException {
		List<LineItem> result = LineItemDao.findAll();
		assertFalse("The retrieved object size should greater than 0", result.isEmpty());
	}
	
	@Test
	public void TestLineItemInsert() throws SQLException {
		Product product = DaoFactory.getItemDao().findById(3); 
		SaleOrder saleOrder = DaoFactory.getSaleOrderDao().findById(1); 
		LineItem testLineItem = new LineItem(product,saleOrder, 12 ); 
		boolean isSucceeded = LineItemDao.create(testLineItem);
		assertTrue("The retrieved object should be added", isSucceeded);
	}
	
	@Test
	public void TestLineItemDelete() throws SQLException {
		boolean isSucceeded = LineItemDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestLineItemUpdate() throws SQLException {
		objectToUpdate.setQuantity(100);
		LineItemDao.update(objectToUpdate);
		
		assertEquals("Should display 100", 100 , LineItemDao.findById(objectToUpdate.getOrder().getId(), objectToUpdate.getProduct().getId()).getQuantity());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		LineItem objectToBeCleanUp = LineItemDao.findById(1,3);
		LineItemDao.delete(objectToBeCleanUp);
		LineItemDao.delete(objectToUpdate);
	}
}
