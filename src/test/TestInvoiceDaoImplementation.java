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

import data_access.InvoiceDaoImplementation;
import data_access.DaoFactory;
import data_access.DatabaseConnection;
import model.Invoice;

public class TestInvoiceDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static InvoiceDaoImplementation invoiceDao = DaoFactory.getInvoiceDao();
	static int generatedIdCreateTest;
	static Invoice objectToDelete;
	static Invoice objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		objectToDelete = new Invoice("invoiceNumber", "2022-03-22 00:00:00:000", 100.00); 
		objectToUpdate = new Invoice("invoiceNumber", "2022-03-22 00:00:00:000", 100.00); 
		invoiceDao.create(objectToDelete);
		invoiceDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestInvoiceFindById() throws SQLException {
		Invoice result = invoiceDao.findById(1);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestInvoiceFindAll() throws SQLException {
		List<Invoice> result = invoiceDao.findAll();
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestInvoiceInsert() throws SQLException {
		Invoice testInvoice = new Invoice("testInvoice","2022-03-22 00:00:00:000", 100.00); 
		generatedIdCreateTest = invoiceDao.create(testInvoice);
		assertNotNull("The retrieved object shouldn't be null", invoiceDao.findById(generatedIdCreateTest));
	}
	
	@Test
	public void TestInvoiceDelete() throws SQLException {
		boolean isSucceeded = invoiceDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestInvoiceUpdate() throws SQLException {
		objectToUpdate.setInvoiceNo("updatedTestingObject");
		invoiceDao.update(objectToUpdate);
		
		assertEquals("Should display updatedTestingObject", "updatedTestingObject" , invoiceDao.findById(objectToUpdate.getId()).getInvoiceNo());
	}
	
	@Test
	public void TestInvoiceSetSaleOrder() throws SQLException {
		Invoice result = invoiceDao.findById(1);
		invoiceDao.setSalesOrderRelatedToThisInvoice(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrder());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Invoice objectToBeCleanUp = invoiceDao.findById(generatedIdCreateTest);
		invoiceDao.delete(objectToBeCleanUp);
		invoiceDao.delete(objectToUpdate);
	}
}
