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
	static ItemDaoImplementation itemDao = DaoFactory.getItemDao();
	static int generatedIdCreateTest;
	static Item objectToDelete;
	static Item objectToUpdate;
	
	@BeforeClass
	public static void CreatingTheTuppleToDelete () throws SQLException {
		objectToDelete = new Item("testItem",10.02,12.56,"Poland",10, 12,"type","description", 1); 
		objectToUpdate = new Item("testItem",10.02,12.56,"Poland",10, 12,"type","description", 1); 
		itemDao.create(objectToDelete);
		itemDao.create(objectToUpdate);
	}
	
	
	@Test
	public void TestItemFindById() throws SQLException {
		Item result = itemDao.findById(3);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestItemFindByType() throws SQLException {
		List<Item> result = itemDao.findByType("Accessories");
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestItemFindAll() throws SQLException {
		List<Item> result = itemDao.findAll();
		assertFalse("The retrieved list shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestItemInsert() throws SQLException {
		Item testItem = new Item("testItem",10.02,12.56,"Poland",10, 12,"type","description", 1); 
		generatedIdCreateTest = itemDao.create(testItem);
		assertNotNull("The retrieved object shouldn't be null", itemDao.findById(generatedIdCreateTest));
	}
	
	@Test
	public void TestItemDelete() throws SQLException {
		boolean isSucceeded = itemDao.delete(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestItemUpdate() throws SQLException {
		objectToUpdate.setType("updatedTestingObject");
		itemDao.update(objectToUpdate);
		
		assertEquals("Should display updatedTestingObject", "updatedTestingObject" , itemDao.findById(objectToUpdate.getId()).getType());
	}
	
	@Test
	public void TestItemSetSupplier() throws SQLException {
		Item result = itemDao.findById(3);
		itemDao.setSupplierRelatedToThisItem(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSupplier());
	}
	
	@Test
	public void TestItemSetLineItem() throws SQLException {
		Item result = itemDao.findById(3);
		itemDao.setLineItemRelatedToThisItem(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrderProductPair());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Item objectToBeCleanUp = itemDao.findById(generatedIdCreateTest);
		itemDao.delete(objectToBeCleanUp);
		itemDao.delete(objectToUpdate);
	}
}
