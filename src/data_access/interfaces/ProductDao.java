package data_access.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Product;

public interface ProductDao {
	int create(Product objectToInsert) throws SQLException;
	void update(Product objectToUpdate) throws SQLException;
	void delete(Product objectToDelete) throws SQLException;
}
