package data_access.interfaces;

import java.sql.SQLException;

import model.Product;

public interface ProductDao {
	int create(Product objectToInsert) throws SQLException;
	boolean update(Product objectToUpdate) throws SQLException;
	boolean delete(Product objectToDelete) throws SQLException;
	void setSupplierRelatedToThisProduct(Product product) throws SQLException;
	void setLineItemRelatedToThisProduct(Product product) throws SQLException;
}
