package data_access;

import java.util.List;

import model.Product;

public interface ProductDao {
	Product findById(int id);
	List<Product> findAll();
	int create(Product objectToInsert);
	void update(Product objectToUpdate);
	void delete(Product objectToDelete);
}
