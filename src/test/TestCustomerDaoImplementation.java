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

import data_access.CustomerDaoImplementation;
import data_access.DaoFactory;
import data_access.DatabaseConnection;
import model.Customer;

public class TestCustomerDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static CustomerDaoImplementation CustomerDao = DaoFactory.getCustomerDao();
	static int generatedIdCreateTest;
	static Customer objectToDelete;
	static Customer objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		objectToDelete = new Customer("testCustomer", "place", "aCity", 9000, "phoneNumber", "club"); 
		objectToUpdate = new Customer("testCustomer", "place", "aCity", 9000, "phoneNumber", "club"); 
		CustomerDao.create(objectToDelete);
		CustomerDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestCustomerFindById() throws SQLException {
		Customer result = CustomerDao.findById(1);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestCustomerFindAll() throws SQLException {
		List<Customer> result = CustomerDao.findAll();
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestCustomerInsert() throws SQLException {
		Customer testCustomer = new Customer("testCustomer", "place", "aCity", 9000, "phoneNumber", "club"); 
		generatedIdCreateTest = CustomerDao.create(testCustomer);
		assertNotNull("The retrieved object shouldn't be null", CustomerDao.findById(generatedIdCreateTest));
	}
	
	@Test
	public void TestCustomerDelete() throws SQLException {
		boolean isSucceeded = CustomerDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestCustomerUpdate() throws SQLException {
		objectToUpdate.setName("updatedTestingObject");
		CustomerDao.update(objectToUpdate);
		
		assertEquals("Should display updatedTestingObject", "updatedTestingObject" , CustomerDao.findById(objectToUpdate.getId()).getName());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Customer objectToBeCleanUp = CustomerDao.findById(generatedIdCreateTest);
		CustomerDao.delete(objectToBeCleanUp);
		CustomerDao.delete(objectToUpdate);
	}
}
