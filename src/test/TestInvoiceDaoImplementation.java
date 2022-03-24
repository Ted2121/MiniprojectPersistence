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
import data_access_layer.InvoiceDaoImplementation;
import model.Invoice;

public class TestInvoiceDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static InvoiceDaoImplementation invoiceDao = DaoFactory.getInvoiceDao();
	static Invoice objectToDelete;
	static Invoice objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		objectToDelete = new Invoice("invoiceNumber", "2022-03-22 00:00:00:000", 100.00); 
		objectToUpdate = new Invoice("invoiceNumber2", "2022-03-22 00:00:00:000", 100.00); 
		invoiceDao.createInvoice(objectToDelete);
		invoiceDao.createInvoice(objectToUpdate);
	}
	
	
	@Test
	public void TestInvoiceFindById() throws SQLException {
		Invoice result = invoiceDao.findInvoiceByInvoiceNumber("18-QUT-0001");
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestInvoiceFindAll() throws SQLException {
		List<Invoice> result = invoiceDao.findAllInvoices();
		assertFalse("The retrievedArrayList shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestInvoiceInsert() throws SQLException {
		Invoice testInvoice = new Invoice("testInvoice","2022-03-22 00:00:00:000", 100.00); 
		invoiceDao.createInvoice(testInvoice);
		assertNotNull("The retrieved object shouldn't be null", invoiceDao.findInvoiceByInvoiceNumber(testInvoice.getInvoiceNo()));
	}
	
	@Test
	public void TestInvoiceDelete() throws SQLException {
		boolean isSucceeded = invoiceDao.deleteInvoice(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestInvoiceUpdate() throws SQLException {
		objectToUpdate.setAmount(12);
		invoiceDao.updateInvoice(objectToUpdate);
		assertTrue("Should display 12", invoiceDao.findInvoiceByInvoiceNumber(objectToUpdate.getInvoiceNo()).getAmount() == 12);
	}
	
	@Test
	public void TestInvoiceSetSaleOrder() throws SQLException {
		Invoice result = invoiceDao.findInvoiceByInvoiceNumber("18-QUT-0001");
		invoiceDao.setSalesOrderRelatedToThisInvoice(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrder());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Invoice objectToBeCleanUp = invoiceDao.findInvoiceByInvoiceNumber("testInvoice");
		invoiceDao.deleteInvoice(objectToBeCleanUp);
		invoiceDao.deleteInvoice(objectToUpdate);
	}
}
