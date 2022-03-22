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
	static SupplierDaoImplementation SupplierDao = DaoFactory.getSupplierDao();
	static int generatedIdCreateTest;
	static Supplier objectToDelete;
	static Supplier objectToUpdate;
	static Customer customer;
	static Invoice invoice;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		objectToDelete = new Supplier("Mark", "place", "country", "phone number", "email@email.dk"); 
		objectToUpdate = new Supplier("Mark", "place", "country", "phone number", "email@email.dk"); 
		SupplierDao.create(objectToDelete);
		SupplierDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestSupplierFindById() throws SQLException {
		Supplier result = SupplierDao.findById(3);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestSupplierFindAll() throws SQLException {
		List<Supplier> result = SupplierDao.findAll();
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestSupplierInsert() throws SQLException {
		Supplier testSupplier = new Supplier("Mark", "place", "country", "phone number", "email@email.dk"); 
		generatedIdCreateTest = SupplierDao.create(testSupplier);
		assertNotNull("The retrieved object shouldn't be null", SupplierDao.findById(generatedIdCreateTest));
	}
	
	@Test
	public void TestSupplierDelete() throws SQLException {
		boolean isSucceeded = SupplierDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestSupplierUpdate() throws SQLException {
		objectToUpdate.setName("UpdatedName");
		SupplierDao.update(objectToUpdate);
		
		assertEquals("Should display UpdatedName", "UpdatedName", SupplierDao.findById(objectToUpdate.getId()).getName());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Supplier objectToBeCleanUp = SupplierDao.findById(generatedIdCreateTest);
		SupplierDao.delete(objectToBeCleanUp);
		SupplierDao.delete(objectToUpdate);
	}
}
