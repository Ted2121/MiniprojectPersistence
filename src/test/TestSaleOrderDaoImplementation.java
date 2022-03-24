package test;

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
import data_access_layer.SaleOrderDaoImplementation;
import model.Customer;
import model.Invoice;
import model.SaleOrder;

public class TestSaleOrderDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static SaleOrderDaoImplementation saleOrderDao = DaoFactory.getSaleOrderDao();
	static int generatedIdCreateTest;
	static SaleOrder objectToDelete;
	static SaleOrder objectToUpdate;
	static Customer customer;
	static Customer customer2;
	static Invoice invoice;
	static Invoice invoice2;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		customer = DaoFactory.getCustomerDao().findAllCustomers().get(1);
		customer2 = DaoFactory.getCustomerDao().findAllCustomers().get(2);
		invoice = DaoFactory.getInvoiceDao().findInvoiceByInvoiceNumber("47-TPH-5629");
		invoice2 = DaoFactory.getInvoiceDao().findInvoiceByInvoiceNumber("48-TPH-5629");
		objectToDelete = new SaleOrder("2022-02-01 00:00:00:000", "2022-02-06 00:00:00:000", false, customer, invoice); 
		objectToUpdate = new SaleOrder("2022-02-01 00:00:00:000", "2022-02-06 00:00:00:000", false, customer2, invoice2); 
		saleOrderDao.createSaleOrder(objectToDelete);
		saleOrderDao.createSaleOrder(objectToUpdate);
	}
	
	
	@Test
	public void TestSaleOrderFindById() throws SQLException {
		SaleOrder result = saleOrderDao.findSaleOrderById(3);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestSaleOrderFindByCustomer() throws SQLException {
		List<SaleOrder> result = saleOrderDao.findSaleOrdersByCustomer(customer);
		assertFalse("The retrievedArrayList shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestSaleOrderFindByInvoice() throws SQLException {
		SaleOrder result = saleOrderDao.findSaleOrderByInvoice(invoice);
		assertNotNull("The retrievedArrayList shouldn't be empty", result);
	}
	
	@Test
	public void TestSaleOrderFindAll() throws SQLException {
		List<SaleOrder> result = saleOrderDao.findAllSaleOrders();
		assertFalse("The retrievedArrayList shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestSaleOrderInsert() throws SQLException {
		SaleOrder testSaleOrder = new SaleOrder("2022-02-01 00:00:00:000", "2022-02-06 00:00:00:000", false, customer, invoice); 
		generatedIdCreateTest = saleOrderDao.createSaleOrder(testSaleOrder);
		assertNotNull("The retrieved object shouldn't be null", saleOrderDao.findSaleOrderById(generatedIdCreateTest));
	}
	
	@Test
	public void TestSaleOrderDelete() throws SQLException {
		boolean isSucceeded = saleOrderDao.deleteSaleOrder(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestSaleOrderUpdate() throws SQLException {
		objectToUpdate.setDeliveryStatus(true);
		saleOrderDao.updateSaleOrder(objectToUpdate);
		
		assertTrue("Should display updatedTestingObject", saleOrderDao.findSaleOrderById(objectToUpdate.getId()).isDeliveryStatus());
	}
	
	@Test
	public void TestSaleOrderSetInvoice() throws SQLException {
		SaleOrder result = saleOrderDao.findSaleOrderById(3);
		saleOrderDao.setInvoiceRelatedToThisSaleOrder(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getInvoice());
	}
	
	@Test
	public void TestSaleOrderSetCustomer() throws SQLException {
		SaleOrder result = saleOrderDao.findSaleOrderById(3);
		saleOrderDao.setCustomerRelatedToThisSaleOrder(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getCustomer());
	}
	
	@Test
	public void TestSaleOrderSetLineItem() throws SQLException {
		SaleOrder result = saleOrderDao.findSaleOrderById(3);
		saleOrderDao.setLineItemRelatedToThisSaleOrder(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getLineItem());
	}
	
	@Test
	public void TestSaleOrderSetAll() throws SQLException {
		SaleOrder result = saleOrderDao.findSaleOrderById(3);
		saleOrderDao.setLineItemRelatedToThisSaleOrder(result);
		saleOrderDao.setInvoiceRelatedToThisSaleOrder(result);
		saleOrderDao.setCustomerRelatedToThisSaleOrder(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getInvoice());
		assertNotNull("The retrieved invoice shouldn't be null", result.getCustomer());
		assertNotNull("The retrieved invoice shouldn't be null", result.getLineItem());
	}
	
	
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		SaleOrder objectToBeCleanUp = saleOrderDao.findSaleOrderById(generatedIdCreateTest);
		saleOrderDao.deleteSaleOrder(objectToBeCleanUp);
		saleOrderDao.deleteSaleOrder(objectToUpdate);
	}
	
}
