package data_access_layer.data_access_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public interface CustomerDao {
	Customer findCustomerById(int id) throws SQLException;
	ArrayList<Customer> findAllCustomers() throws SQLException;
	int createCustomer(Customer objectToInsert) throws SQLException;
	boolean updateCustomer(Customer objectToUpdate) throws SQLException;
	boolean deleteCustomer(Customer objectToDelete) throws SQLException;
	void setSalesOrderRelatedToThisCustomer(Customer customer) throws SQLException;
}
