package data_access.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.Supplier;

public interface SupplierDao {
	Supplier findById(int id) throws SQLException;
	List<Supplier> findAll() throws SQLException;
	int create(Supplier objectToInsert) throws SQLException;
	boolean update(Supplier objectToUpdate) throws SQLException;
	boolean delete(Supplier objectToDelete) throws SQLException;
}
