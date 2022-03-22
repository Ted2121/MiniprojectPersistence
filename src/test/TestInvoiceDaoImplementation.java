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
	static InvoiceDaoImplementation InvoiceDao = DaoFactory.getInvoiceDao();
	static int generatedIdCreateTest;
	static Invoice objectToDelete;
	static Invoice objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		objectToDelete = new Invoice("invoiceNumber", "2022-03-22 00:00:00:000", 100.00); 
		objectToUpdate = new Invoice("invoiceNumber", "2022-03-22 00:00:00:000", 100.00); 
		InvoiceDao.create(objectToDelete);
		InvoiceDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestInvoiceFindById() throws SQLException {
		Invoice result = InvoiceDao.findById(1);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestInvoiceFindAll() throws SQLException {
		List<Invoice> result = InvoiceDao.findAll();
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestInvoiceInsert() throws SQLException {
		Invoice testInvoice = new Invoice("testInvoice","2022-03-22 00:00:00:000", 100.00); 
		generatedIdCreateTest = InvoiceDao.create(testInvoice);
		assertNotNull("The retrieved object shouldn't be null", InvoiceDao.findById(generatedIdCreateTest));
	}
	
	@Test
	public void TestInvoiceDelete() throws SQLException {
		boolean isSucceeded = InvoiceDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestInvoiceUpdate() throws SQLException {
		objectToUpdate.setInvoiceNo("updatedTestingObject");
		InvoiceDao.update(objectToUpdate);
		
		assertEquals("Should display updatedTestingObject", "updatedTestingObject" , InvoiceDao.findById(objectToUpdate.getId()).getInvoiceNo());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Invoice objectToBeCleanUp = InvoiceDao.findById(generatedIdCreateTest);
		InvoiceDao.delete(objectToBeCleanUp);
		InvoiceDao.delete(objectToUpdate);
	}
}
