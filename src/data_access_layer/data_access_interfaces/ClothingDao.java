package data_access_layer.data_access_interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Clothing;

public interface ClothingDao {
	Clothing findClothingById(int id) throws SQLException;
	List<Clothing> findAllClothings() throws SQLException;
	int createClothing(Clothing objectToInsert) throws SQLException;
	boolean updateClothing(Clothing objectToUpdate) throws SQLException;
	boolean deleteClothing(Clothing objectToDelete) throws SQLException;
	void setSupplierRelatedToThisClothing(Clothing clothing) throws SQLException;
	void setLineItemRelatedToThisClothing(Clothing clothing) throws SQLException;
}
