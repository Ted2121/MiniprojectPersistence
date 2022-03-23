package data_access.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Clothing;
import model.Item;
import model.Product;

public interface ClothingDao {
	Clothing findById(int id) throws SQLException;
	List<Clothing> findAll() throws SQLException;
	int create(Clothing objectToInsert) throws SQLException;
	boolean update(Clothing objectToUpdate) throws SQLException;
	boolean delete(Clothing objectToDelete) throws SQLException;
	void setSupplierRelatedToThisClothing(Clothing clothing) throws SQLException;
	void setSaleOrder_ProductRelatedToThisClothing(Clothing clothing) throws SQLException;
}
