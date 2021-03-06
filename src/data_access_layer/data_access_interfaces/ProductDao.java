package data_access_layer.data_access_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;
import model.Supplier;

public interface ProductDao {
	int createProduct(Product objectToInsert) throws SQLException;
	boolean updateProduct(Product objectToUpdate) throws SQLException;
	boolean deleteProduct(Product objectToDelete) throws SQLException;
	void setSupplierRelatedToThisProduct(Product product) throws SQLException;
	void setLineItemRelatedToThisProduct(Product product) throws SQLException;
}
