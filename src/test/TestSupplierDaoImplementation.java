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
import data_access_layer.SupplierDaoImplementation;
import model.Customer;
import model.Invoice;
import model.SaleOrder;
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
	public static void CreatingTheTupleToDelete () throws SQLException {
		objectToDelete = new Supplier("Mark", "place", "country", "phone number", "email@email.dk"); 
		objectToUpdate = new Supplier("Mark", "place", "country", "phone number", "email@email.dk"); 
		supplierDao.createSupplier(objectToDelete);
		supplierDao.createSupplier(objectToUpdate);
	}
	
	
	@Test
	public void TestSupplierFindById() throws SQLException {
		Supplier result = supplierDao.findSupplierById(3);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestSupplierFindAll() throws SQLException {
		List<Supplier> result = supplierDao.findAllSuppliers();
		assertFalse("The retrievedArrayList shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestSupplierInsert() throws SQLException {
		Supplier testSupplier = new Supplier("Mark", "place", "country", "phone number", "email@email.dk"); 
		generatedIdCreateTest = supplierDao.createSupplier(testSupplier);
		assertNotNull("The retrieved object shouldn't be null", supplierDao.findSupplierById(generatedIdCreateTest));
	}
	
	@Test
	public void TestSupplierDelete() throws SQLException {
		boolean isSucceeded = supplierDao.deleteSupplier(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestSupplierUpdate() throws SQLException {
		objectToUpdate.setName("UpdatedName");
		supplierDao.updateSupplier(objectToUpdate);
		
		assertEquals("Should display UpdatedName", "UpdatedName", supplierDao.findSupplierById(objectToUpdate.getId()).getName());
	}
	
	@Test
	public void TestSupplierSetProducts() throws SQLException {
		Supplier result = supplierDao.findSupplierById(3);
		supplierDao.setProductsRelatedToThisSupplier(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getProducts());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Supplier objectToBeCleanUp = supplierDao.findSupplierById(generatedIdCreateTest);
		supplierDao.deleteSupplier(objectToBeCleanUp);
		supplierDao.deleteSupplier(objectToUpdate);
	}
}
