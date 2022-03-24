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

import data_access_layer.CustomerDaoImplementation;
import data_access_layer.DaoFactory;
import data_access_layer.DatabaseConnection;
import model.Customer;

public class TestCustomerDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static CustomerDaoImplementation customerDao = DaoFactory.getCustomerDao();
	static int generatedIdCreateTest;
	static Customer objectToDelete;
	static Customer objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTupleToDelete () throws SQLException {
		objectToDelete = new Customer("testCustomer", "place", "aCity", 9000, "phoneNumber", "club"); 
		objectToUpdate = new Customer("testCustomer", "place", "aCity", 9000, "phoneNumber", "club"); 
		customerDao.createCustomer(objectToDelete);
		customerDao.createCustomer(objectToUpdate);
	}
	
	
	@Test
	public void TestCustomerFindById() throws SQLException {
		Customer result = customerDao.findCustomerById(1);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestCustomerFindAll() throws SQLException {
		List<Customer> result = customerDao.findAllCustomers();
		assertFalse("The retrievedArrayList shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestCustomerInsert() throws SQLException {
		Customer testCustomer = new Customer("testCustomer", "place", "aCity", 9000, "phoneNumber", "club"); 
		generatedIdCreateTest = customerDao.createCustomer(testCustomer);
		assertNotNull("The retrieved object shouldn't be null", customerDao.findCustomerById(generatedIdCreateTest));
	}
	
	@Test
	public void TestCustomerDelete() throws SQLException {
		boolean isSucceeded = customerDao.deleteCustomer(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestCustomerUpdate() throws SQLException {
		objectToUpdate.setName("updatedTestingObject");
		customerDao.updateCustomer(objectToUpdate);
		
		assertEquals("Should display updatedTestingObject", "updatedTestingObject" , customerDao.findCustomerById(objectToUpdate.getId()).getName());
	}
	
	@Test
	public void TestCustomerSetSaleOrder() throws SQLException {
		Customer result = customerDao.findCustomerById(1);
		customerDao.setSalesOrderRelatedToThisCustomer(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrders());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Customer objectToBeCleanUp = customerDao.findCustomerById(generatedIdCreateTest);
		customerDao.deleteCustomer(objectToBeCleanUp);
		customerDao.deleteCustomer(objectToUpdate);
	}
}
