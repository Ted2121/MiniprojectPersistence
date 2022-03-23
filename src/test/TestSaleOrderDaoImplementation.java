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

import data_access.SaleOrderDaoImplementation;
import data_access.DaoFactory;
import data_access.DatabaseConnection;
import model.Customer;
import model.Invoice;
import model.SaleOrder;

public class TestSaleOrderDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static SaleOrderDaoImplementation SaleOrderDao = DaoFactory.getSaleOrderDao();
	static int generatedIdCreateTest;
	static SaleOrder objectToDelete;
	static SaleOrder objectToUpdate;
	static Customer customer;
	static Invoice invoice;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		customer = DaoFactory.getCustomerDao().findAll().get(1);
		invoice = DaoFactory.getInvoiceDao().findAll().get(0);
		objectToDelete = new SaleOrder("2022-02-01 00:00:00:000", "2022-02-06 00:00:00:000", false, customer, invoice); 
		objectToUpdate = new SaleOrder("2022-02-01 00:00:00:000", "2022-02-06 00:00:00:000", false, customer, invoice); 
		SaleOrderDao.create(objectToDelete);
		SaleOrderDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestSaleOrderFindById() throws SQLException {
		SaleOrder result = SaleOrderDao.findById(3);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestSaleOrderFindByCustomer() throws SQLException {
		List<SaleOrder> result = SaleOrderDao.findByCustomer(customer);
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestSaleOrderFindAll() throws SQLException {
		List<SaleOrder> result = SaleOrderDao.findAll();
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestSaleOrderInsert() throws SQLException {
		SaleOrder testSaleOrder = new SaleOrder("2022-02-01 00:00:00:000", "2022-02-06 00:00:00:000", false, customer, invoice); 
		generatedIdCreateTest = SaleOrderDao.create(testSaleOrder);
		assertNotNull("The retrieved object shouldn't be null", SaleOrderDao.findById(generatedIdCreateTest));
	}
	
	@Test
	public void TestSaleOrderDelete() throws SQLException {
		boolean isSucceeded = SaleOrderDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestSaleOrderUpdate() throws SQLException {
		objectToUpdate.setDeliveryStatus(true);
		SaleOrderDao.update(objectToUpdate);
		
		assertTrue("Should display updatedTestingObject", SaleOrderDao.findById(objectToUpdate.getId()).isDeliveryStatus());
	}
	
	@Test
	public void TestSaleOrderSetInvoice() throws SQLException {
		SaleOrder result = SaleOrderDao.findById(3);
		SaleOrderDao.setInvoiceRelatedToThisSaleOrder(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getInvoice());
	}
	
	@Test
	public void TestSaleOrderSetCustomer() throws SQLException {
		SaleOrder result = SaleOrderDao.findById(3);
		SaleOrderDao.setCustomerRelatedToThisSaleOrder(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getCustomer());
	}
	
	@Test
	public void TestSaleOrderSetSaleOrder_Product() throws SQLException {
		SaleOrder result = SaleOrderDao.findById(3);
		SaleOrderDao.setSaleOrder_ProductRelatedToThisSaleOrder(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrderProductPair());
	}
	
	@Test
	public void TestSaleOrderSetAll() throws SQLException {
		SaleOrder result = SaleOrderDao.findById(3);
		SaleOrderDao.setSaleOrder_ProductRelatedToThisSaleOrder(result);
		SaleOrderDao.setInvoiceRelatedToThisSaleOrder(result);
		SaleOrderDao.setCustomerRelatedToThisSaleOrder(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getInvoice());
		assertNotNull("The retrieved invoice shouldn't be null", result.getCustomer());
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrderProductPair());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		SaleOrder objectToBeCleanUp = SaleOrderDao.findById(generatedIdCreateTest);
		SaleOrderDao.delete(objectToBeCleanUp);
		SaleOrderDao.delete(objectToUpdate);
	}
}
