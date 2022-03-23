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

import data_access.SaleOrder_ProductDaoImplementation;
import data_access.DaoFactory;
import data_access.DatabaseConnection;
import model.Product;
import model.SaleOrder;
import model.SaleOrder_Product;

public class TestSaleOrder_ProductDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static SaleOrder_ProductDaoImplementation SaleOrder_ProductDao = DaoFactory.getSaleOrder_ProductDao();
	static int generatedIdCreateTest;
	static SaleOrder_Product objectToDelete;
	static SaleOrder_Product objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		Product product = DaoFactory.getClothingDao().findById(1); 
		SaleOrder saleOrder = DaoFactory.getSaleOrderDao().findById(3);
		Product product2 = DaoFactory.getClothingDao().findById(2);
		SaleOrder saleOrder2 = DaoFactory.getSaleOrderDao().findById(2); 
		objectToDelete = new SaleOrder_Product(product,saleOrder, 12 ); 
		objectToUpdate = new SaleOrder_Product(product2,saleOrder2, 12 ); 
		SaleOrder_ProductDao.create(objectToDelete);
		SaleOrder_ProductDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestSaleOrder_ProductFindBySaleOrder() throws SQLException {
		SaleOrder saleOrder = DaoFactory.getSaleOrderDao().findById(1); 
		List<SaleOrder_Product> result = SaleOrder_ProductDao.findBySaleOrder(saleOrder);
		assertFalse("The retrieved object size should greater than 0", result.isEmpty());
	}
	
	@Test
	public void TestSaleOrder_ProductFindByProduct() throws SQLException {
		Product product = DaoFactory.getClothingDao().findById(1); 
		List<SaleOrder_Product> result = SaleOrder_ProductDao.findByProduct(product);
		assertFalse("The retrieved object size should greater than 0", result.isEmpty());
	}
	
	@Test
	public void TestSaleOrder_ProductById() throws SQLException {
		SaleOrder_Product result = SaleOrder_ProductDao.findById(1,1);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestSaleOrder_ProductFindAll() throws SQLException {
		List<SaleOrder_Product> result = SaleOrder_ProductDao.findAll();
		assertFalse("The retrieved object size should greater than 0", result.isEmpty());
	}
	
	@Test
	public void TestSaleOrder_ProductInsert() throws SQLException {
		Product product = DaoFactory.getItemDao().findById(3); 
		SaleOrder saleOrder = DaoFactory.getSaleOrderDao().findById(1); 
		SaleOrder_Product testSaleOrder_Product = new SaleOrder_Product(product,saleOrder, 12 ); 
		boolean isSucceeded = SaleOrder_ProductDao.create(testSaleOrder_Product);
		assertTrue("The retrieved object should be added", isSucceeded);
	}
	
	@Test
	public void TestSaleOrder_ProductDelete() throws SQLException {
		boolean isSucceeded = SaleOrder_ProductDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestSaleOrder_ProductUpdate() throws SQLException {
		objectToUpdate.setQuantity(100);
		SaleOrder_ProductDao.update(objectToUpdate);
		
		assertEquals("Should display 100", 100 , SaleOrder_ProductDao.findById(objectToUpdate.getOrder().getId(), objectToUpdate.getProduct().getId()).getQuantity());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		SaleOrder_Product objectToBeCleanUp = SaleOrder_ProductDao.findById(1,3);
		SaleOrder_ProductDao.delete(objectToBeCleanUp);
		SaleOrder_ProductDao.delete(objectToUpdate);
	}
}
