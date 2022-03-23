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

import data_access.SupplierDaoImplementation;
import data_access.DaoFactory;
import data_access.DatabaseConnection;
import model.Customer;
import model.Invoice;
import model.Supplier;

public class TestSupplierDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static SupplierDaoImplementation supplierDao = DaoFactory.getSupplierDao();
	static int generatedIdCreateTest;
	static Supplier objectToDelete;
	static Supplier objectToUpdate;
	static Customer customer;
	static Invoice invoice;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		objectToDelete = new Supplier("Mark", "place", "country", "phone number", "email@email.dk"); 
		objectToUpdate = new Supplier("Mark", "place", "country", "phone number", "email@email.dk"); 
		supplierDao.create(objectToDelete);
		supplierDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestSupplierFindById() throws SQLException {
		Supplier result = supplierDao.findById(3);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestSupplierFindAll() throws SQLException {
		List<Supplier> result = supplierDao.findAll();
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestSupplierInsert() throws SQLException {
		Supplier testSupplier = new Supplier("Mark", "place", "country", "phone number", "email@email.dk"); 
		generatedIdCreateTest = supplierDao.create(testSupplier);
		assertNotNull("The retrieved object shouldn't be null", supplierDao.findById(generatedIdCreateTest));
	}
	
	@Test
	public void TestSupplierDelete() throws SQLException {
		boolean isSucceeded = supplierDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestSupplierUpdate() throws SQLException {
		objectToUpdate.setName("UpdatedName");
		supplierDao.update(objectToUpdate);
		
		assertEquals("Should display UpdatedName", "UpdatedName", supplierDao.findById(objectToUpdate.getId()).getName());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Supplier objectToBeCleanUp = supplierDao.findById(generatedIdCreateTest);
		supplierDao.delete(objectToBeCleanUp);
		supplierDao.delete(objectToUpdate);
	}
}
