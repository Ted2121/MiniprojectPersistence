package data_access;

import java.util.List;

import model.Customer;

public interface IF_CustomerDao {
	Customer findById(int id);
	List<Customer> findAll();
	int create(Customer objectToInsert);
	void update(Customer objectToUpdate);
	void delete(Customer objectToDelete);
}
