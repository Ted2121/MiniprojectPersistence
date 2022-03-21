package data_access.interfaces;

import java.util.List;

import model.Customer;

public interface CustomerDao {
	Customer findById(int id);
	List<Customer> findAll();
	int create(Customer objectToInsert);
	void update(Customer objectToUpdate);
	void delete(Customer objectToDelete);
}