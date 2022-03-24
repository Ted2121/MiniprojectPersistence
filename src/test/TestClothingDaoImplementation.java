package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import data_access_layer.ClothingDaoImplementation;
import data_access_layer.DaoFactory;
import data_access_layer.DatabaseConnection;
import model.Clothing;
import model.Supplier;

public class TestClothingDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static ClothingDaoImplementation clothingDao = DaoFactory.getClothingDao();
	static int generatedIdCreateTest;
	static Clothing objectToDelete;
	static Clothing objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTupleToDelete () throws SQLException {
		objectToDelete = new Clothing("testClothing",10.02,12.56,"Poland",10, 12,"M","red", 1); 
		objectToUpdate = new Clothing("testClothing",10.02,12.56,"Poland",10, 12,"M","red", 2); 
		clothingDao.createClothing(objectToDelete);
		clothingDao.createClothing(objectToUpdate);
	}
	
	
	@Test
	public void TestClothingFindById() throws SQLException {
		Clothing result = clothingDao.findClothingById(1);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestClothingFindAll() throws SQLException {
		List<Clothing> result = clothingDao.findAllClothings();
		assertFalse("The retrieved ArrayList shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestClothingFindBySupplier() throws SQLException {
		Supplier supplier = new Supplier("ttt", "ttt", "ttt", "ttt", "ttt");
		supplier.setId(1);
		ArrayList<Clothing> result = clothingDao.findClothingsBySupplier(supplier);
		assertFalse("The retrieved object shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestClothingInsert() throws SQLException {
		Clothing testClothing = new Clothing("testClothing",10.02,12.56,"Poland",10, 12,"M","red", 3); 
		generatedIdCreateTest = clothingDao.createClothing(testClothing);
		assertNotNull("The retrieved object shouldn't be null", clothingDao.findClothingById(generatedIdCreateTest));
	}
	
	@Test
	public void TestClothingDelete() throws SQLException {
		boolean isSucceeded = clothingDao.deleteClothing(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestClothingUpdate() throws SQLException {
		objectToUpdate.setName("updatedTestingObject");
		clothingDao.updateClothing(objectToUpdate);
		
		assertEquals("Should display updatedTestingObject", "updatedTestingObject" , clothingDao.findClothingById(objectToUpdate.getId()).getName());
	}
	
	@Test
	public void TestClothingSetSupplier() throws SQLException {
		Clothing result = clothingDao.findClothingById(1);
		clothingDao.setSupplierRelatedToThisClothing(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSupplier());
	}
	
	@Test
	public void TestClothingSetLineItem() throws SQLException {
		Clothing result = clothingDao.findClothingById(1);
		clothingDao.setLineItemRelatedToThisClothing(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrderProductPair());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Clothing objectToBeCleanUp = clothingDao.findClothingById(generatedIdCreateTest);
		clothingDao.deleteClothing(objectToBeCleanUp);
		clothingDao.deleteClothing(objectToUpdate);
	}
}
