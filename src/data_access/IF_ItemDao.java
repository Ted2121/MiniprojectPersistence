package data_access;

import java.util.List;

import model.Item;

public interface IF_ItemDao {
	Item findById(int id);
	List<Item> findAll();
	List<Item> findByType(String type);
	int create(Item objectToInsert);
	void update(Item objectToUpdate);
	void delete(Item objectToDelete);
}
