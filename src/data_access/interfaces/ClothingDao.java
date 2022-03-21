package data_access.interfaces;

import java.util.List;

import model.Clothing;

public interface ClothingDao {
	Clothing findById(int id);
	List<Clothing> findAll();
	int create(Clothing objectToInsert);
	void update(Clothing objectToUpdate);
	void delete(Clothing objectToDelete);
}
