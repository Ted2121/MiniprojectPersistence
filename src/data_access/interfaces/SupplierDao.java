package data_access.interfaces;

import java.util.List;

import model.Product;

public interface SupplierDao {
	Product findById(int id);
	List<Product> findAll();
	int create(Product objectToInsert);
	void update(Product objectToUpdate);
	void delete(Product objectToDelete);
}
