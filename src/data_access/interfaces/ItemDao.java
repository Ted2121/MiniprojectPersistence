package data_access.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Item;
import model.Product;

public interface ItemDao {
	Item findById(int id) throws SQLException;
	List<Item> findAll() throws SQLException;
	List<Item> findByType(String type) throws SQLException;
	int create(Item objectToInsert) throws SQLException;
	boolean update(Item objectToUpdate) throws SQLException;
	boolean delete(Item objectToDelete) throws SQLException;
	void setSupplierRelatedToThisItem(Item item) throws SQLException;
	void setSaleOrder_ProductRelatedToThisItem(Item item) throws SQLException;
}
