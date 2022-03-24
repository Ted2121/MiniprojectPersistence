package data_access_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_access_layer.data_access_interfaces.ClothingDao;
import model.Clothing;

public class ClothingDaoImplementation implements ClothingDao{
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private ArrayList<Clothing> buildObjects(ResultSet rs) throws SQLException{
		ArrayList<Clothing> clothingList = new ArrayList<Clothing>();
		while(rs.next()) {
			clothingList.add(buildObject(rs));
		}
		
		return clothingList;
	}
	
	private Clothing buildObject(ResultSet rs) throws SQLException{
		
		Clothing buildedObject = new Clothing(rs.getInt("id"),rs.getString("name"), rs.getDouble("purchasePrice"), rs.getDouble("salesPrice"),
				rs.getString("countryOfOrigin"), rs.getInt("minStock"), rs.getInt("stock"), rs.getString("size"), rs.getString("color"), rs.getInt("FK_Supplier"));
		buildedObject.setFK_Supplier(rs.getInt("FK_Supplier"));
		return buildedObject;
	}

	@Override
	public Clothing findClothingById(int id) throws SQLException {
		String query = "SELECT * FROM Clothing INNER JOIN Product ON Clothing.id = Product.id WHERE Clothing.id = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, id);
		ResultSet rs = preparedSelectStatement.executeQuery();
		Clothing retrievedClothing = null;
		while(rs.next()) {
			retrievedClothing = buildObject(rs);
		}
		
		return retrievedClothing;
	}

	@Override
	public ArrayList<Clothing> findAllClothings() throws SQLException {
		String query = "SELECT * FROM Clothing INNER JOIN Product ON Clothing.id = Product.id";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		ArrayList<Clothing> retrievedClothingList = buildObjects(rs);

		return retrievedClothingList;
	}

	@Override
	public int createClothing(Clothing objectToInsert) throws SQLException {
		
		int generatedId = productDao.createProduct(objectToInsert);
		
		String sqlInsertClothingStatement = "INSERT INTO Clothing(size, color, id)"
				+ "VALUES(?, ?, ?);";
		PreparedStatement preparedInsertClothingStatement = connectionDB.prepareStatement(sqlInsertClothingStatement);
		preparedInsertClothingStatement.setString(1, objectToInsert.getSize());
		preparedInsertClothingStatement.setString(2, objectToInsert.getColor());
		preparedInsertClothingStatement.setInt(3, generatedId);
		
		preparedInsertClothingStatement.execute();
		
		objectToInsert.setId(generatedId);
		
		return generatedId;
	}

	@Override
	public boolean updateClothing(Clothing objectToUpdate) throws SQLException {
		productDao.updateProduct(objectToUpdate);
		
		String sqlUpdateClothingStatement = "UPDATE Clothing SET [size] = ?, color = ? WHERE id = ?";
		PreparedStatement preparedUpdateClothingStatement = connectionDB.prepareStatement(sqlUpdateClothingStatement);
		preparedUpdateClothingStatement.setString(1, objectToUpdate.getSize());
		preparedUpdateClothingStatement.setString(2, objectToUpdate.getColor());
		preparedUpdateClothingStatement.setInt(3, objectToUpdate.getId());

		preparedUpdateClothingStatement.execute();
		return true;
	}

	@Override
	public boolean deleteClothing(Clothing objectToDelete) throws SQLException {
		productDao.deleteProduct(objectToDelete);

		String sqlDeleteClothingStatement = "DELETE FROM Product WHERE id = ?";
		PreparedStatement preparedDeleteClothingStatement = connectionDB.prepareStatement(sqlDeleteClothingStatement);
		preparedDeleteClothingStatement.setInt(1, objectToDelete.getId());
		preparedDeleteClothingStatement.execute();
		return true;
	}

	@Override
	public void setSupplierRelatedToThisClothing(Clothing clothing) throws SQLException {
		DaoFactory.getProductDao().setSupplierRelatedToThisProduct(clothing);
		
	}

	@Override
	public void setLineItemRelatedToThisClothing(Clothing clothing) throws SQLException {
		DaoFactory.getProductDao().setLineItemRelatedToThisProduct(clothing);
		
	}


}
