package data_access.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Customer;

public interface CustomerDao {
	Customer findById(int id) throws SQLException;
	List<Customer> findAll() throws SQLException;
	int create(Customer objectToInsert) throws SQLException;
	boolean update(Customer objectToUpdate) throws SQLException;
	boolean delete(Customer objectToDelete) throws SQLException;
}
