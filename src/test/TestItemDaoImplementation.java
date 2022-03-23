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

import data_access.ItemDaoImplementation;
import data_access.DaoFactory;
import data_access.DatabaseConnection;
import model.Item;

public class TestItemDaoImplementation {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	static ItemDaoImplementation ItemDao = DaoFactory.getItemDao();
	static int generatedIdCreateTest;
	static Item objectToDelete;
	static Item objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		objectToDelete = new Item("testItem",10.02,12.56,"Poland",10, 12,"type","description"); 
		objectToUpdate = new Item("testItem",10.02,12.56,"Poland",10, 12,"type","description"); 
		ItemDao.create(objectToDelete);
		ItemDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestItemFindById() throws SQLException {
		Item result = ItemDao.findById(3);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestItemFindByType() throws SQLException {
		List<Item> result = ItemDao.findByType("Accessories");
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestItemFindAll() throws SQLException {
		List<Item> result = ItemDao.findAll();
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestItemInsert() throws SQLException {
		Item testItem = new Item("testItem",10.02,12.56,"Poland",10, 12,"type","description"); 
		generatedIdCreateTest = ItemDao.create(testItem);
		assertNotNull("The retrieved object shouldn't be null", ItemDao.findById(generatedIdCreateTest));
	}
	
	@Test
	public void TestItemDelete() throws SQLException {
		boolean isSucceeded = ItemDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestItemUpdate() throws SQLException {
		objectToUpdate.setType("updatedTestingObject");
		ItemDao.update(objectToUpdate);
		
		assertEquals("Should display updatedTestingObject", "updatedTestingObject" , ItemDao.findById(objectToUpdate.getId()).getType());
	}
	
	@Test
	public void TestItemSetSupplier() throws SQLException {
		Item result = ItemDao.findById(3);
		ItemDao.setSupplierRelatedToThisItem(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSupplier());
	}
	
	@Test
	public void TestItemSetLineItem() throws SQLException {
		Item result = ItemDao.findById(3);
		ItemDao.setLineItemRelatedToThisItem(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrderProductPair());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Item objectToBeCleanUp = ItemDao.findById(generatedIdCreateTest);
		ItemDao.delete(objectToBeCleanUp);
		ItemDao.delete(objectToUpdate);
	}
}
