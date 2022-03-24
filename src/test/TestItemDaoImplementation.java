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
import data_access_layer.ItemDaoImplementation;
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
		itemDao.createItem(objectToDelete);
		itemDao.createItem(objectToUpdate);
	}
	
	
	@Test
	public void TestItemFindById() throws SQLException {
		Item result = itemDao.findItemById(3);
		assertNotNull("The retrieved object shouldn't be null", result);
	}
	
	@Test
	public void TestItemFindByType() throws SQLException {
		List<Item> result = itemDao.findItemsByType("Accessories");
		assertFalse("The retrievedArrayList shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestItemFindAll() throws SQLException {
		List<Item> result = itemDao.findAllItems();
		assertFalse("The retrievedArrayList shouldn't be empty", result.isEmpty());
	}
	
	@Test
	public void TestItemInsert() throws SQLException {
		Item testItem = new Item("testItem",10.02,12.56,"Poland",10, 12,"type","description", 1); 
		generatedIdCreateTest = itemDao.createItem(testItem);
		assertNotNull("The retrieved object shouldn't be null", itemDao.findItemById(generatedIdCreateTest));
	}
	
	@Test
	public void TestItemDelete() throws SQLException {
		boolean isSucceeded = itemDao.deleteItem(objectToDelete);
		assertTrue("Should have deletes the object ", isSucceeded);
	}
	
	@Test
	public void TestItemUpdate() throws SQLException {
		objectToUpdate.setType("updatedTestingObject");
		itemDao.updateItem(objectToUpdate);
		
		assertEquals("Should display updatedTestingObject", "updatedTestingObject" , itemDao.findItemById(objectToUpdate.getId()).getType());
	}
	
	@Test
	public void TestItemSetSupplier() throws SQLException {
		Item result = itemDao.findItemById(3);
		itemDao.setSupplierRelatedToThisItem(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSupplier());
	}
	
	@Test
	public void TestItemSetLineItem() throws SQLException {
		Item result = itemDao.findItemById(3);
		itemDao.setLineItemRelatedToThisItem(result);
		assertNotNull("The retrieved invoice shouldn't be null", result.getSaleOrderProductPair());
	}
	
	@AfterClass
	public static void CleanUp() throws SQLException {
		Item objectToBeCleanUp = itemDao.findItemById(generatedIdCreateTest);
		itemDao.deleteItem(objectToBeCleanUp);
		itemDao.deleteItem(objectToUpdate);
	}
}
