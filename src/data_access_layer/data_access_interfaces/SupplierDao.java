package data_access_layer.data_access_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Supplier;

public interface SupplierDao {
	Supplier findSupplierById(int id) throws SQLException;
	ArrayList<Supplier> findAllSuppliers() throws SQLException;
	int createSupplier(Supplier objectToInsert) throws SQLException;
	boolean updateSupplier(Supplier objectToUpdate) throws SQLException;
	boolean deleteSupplier(Supplier objectToDelete) throws SQLException;
}
