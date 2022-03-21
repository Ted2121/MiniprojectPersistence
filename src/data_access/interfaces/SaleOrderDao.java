package data_access.interfaces;

import java.util.List;

import model.Customer;
import model.SaleOrder;

public interface SaleOrderDao {
	SaleOrder findById(int id);
	List<SaleOrder> findAll();
	List<SaleOrder> findByCustomer(Customer customer);
	int create(SaleOrder objectToInsert);
	void update(SaleOrder objectToUpdate);
	void delete(SaleOrder objectToDelete);
}