package data_access_layer.data_access_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public interface ItemDao {
	Item findItemById(int id) throws SQLException;
	ArrayList<Item> findAllItems() throws SQLException;
	ArrayList<Item> findItemsByType(String type) throws SQLException;
	int createItem(Item objectToInsert) throws SQLException;
	boolean updateItem(Item objectToUpdate) throws SQLException;
	boolean deleteItem(Item objectToDelete) throws SQLException;
	void setSupplierRelatedToThisItem(Item item) throws SQLException;
	void setLineItemRelatedToThisItem(Item item) throws SQLException;
}
