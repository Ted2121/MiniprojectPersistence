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

import data_access.ClothingDaoImplementation;
import data_access.DaoFactory;
import data_access.DatabaseConnection;
import model.Clothing;

public class TestClothingDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static ClothingDaoImplementation clothingDao = DaoFactory.getClothingDao();
	static int generatedIdCreateTest;
	static Clothing objectToDelete;
	static Clothing objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		objectToDelete = new Clothing("testClothing",10.02,12.56,"Poland",10, 12,"M","red"); 
		objectToUpdate = new Clothing("testClothing",10.02,12.56,"Poland",10, 12,"M","red"); 
		clothingDao.create(objectToDelete);
		clothingDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestClothingFindById() throws SQLException {
		Clothing result = clothingDao.findById(1);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestClothingFindAll() throws SQLException {
		List<Clothing> result = clothingDao.findAll();
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestClothingInsert() throws SQLException {
		Clothing testClothing = new Clothing("testClothing",10.02,12.56,"Poland",10, 12,"M","red"); 
		generatedIdCreateTest = clothingDao.create(testClothing);
		assertNotNull("The retrieved object shouldn't be null", clothingDao.findById(generatedIdCreateTest));
	}
	
	@Test
	public void TestClothingDelete() throws SQLException {
		boolean isSucceeded = clothingDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestClothingUpdate() throws SQLException {
		objectToUpdate.setName("updatedTestingObject");
		clothingDao.update(objectToUpdate);
		
		assertEquals("Should display updatedTestingObject", "updatedTestingObject" , clothingDao.findById(objectToUpdate.getId()).getName());
	}
	
	@Test
	public void TestClothingSetSupplier() throws SQLException {
		Clothing result = clothingDao.findById(1);
		clothingDao.setSupplierRelatedToThisClothing(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSupplier());
	}
	
	@Test
	public void TestClothingSetLineItem() throws SQLException {
		Clothing result = clothingDao.findById(1);
		clothingDao.setLineItemRelatedToThisClothing(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrderProductPair());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Clothing objectToBeCleanUp = clothingDao.findById(generatedIdCreateTest);
		clothingDao.delete(objectToBeCleanUp);
		clothingDao.delete(objectToUpdate);
	}
}
