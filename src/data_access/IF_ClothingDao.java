package data_access;

import java.util.List;

import model.Clothing;

public interface IF_ClothingDao {
	Clothing findById(int id);
	List<Clothing> findAll();
	int create(Clothing objectToInsert);
	void update(Clothing objectToUpdate);
	void delete(Clothing objectToDelete);
}
