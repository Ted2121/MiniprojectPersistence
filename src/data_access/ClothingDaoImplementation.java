package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data_access.interfaces.ClothingDao;
import model.Clothing;

public class ClothingDaoImplementation implements ClothingDao{
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private List<Clothing> buildObjects(ResultSet rs) throws SQLException{
		List<Clothing> clothingList = new ArrayList<Clothing>();
		while(rs.next()) {
			clothingList.add(buildObject(rs));
		}
		
		return clothingList;
	}
	
	private Clothing buildObject(ResultSet rs) throws SQLException{
		
		Clothing buildedObject = new Clothing(rs.getInt("id"),rs.getString("name"), rs.getDouble("purchasePrice"), rs.getDouble("salesPrice"),
				rs.getString("countryOfOrigin"), rs.getInt("minStock"), rs.getInt("stock"), rs.getString("size"), rs.getString("color"));
		
		return buildedObject;
	}

	@Override
	public Clothing findById(int id) throws SQLException {
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
	public List<Clothing> findAll() throws SQLException {
		String query = "SELECT * FROM Clothing INNER JOIN Product ON Clothing.id = Product.id";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<Clothing> retrievedClothingList = buildObjects(rs);

		return retrievedClothingList;
	}

	@Override
	public int create(Clothing objectToInsert) throws SQLException {
		
		int generatedId = productDao.create(objectToInsert);
		
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
	public boolean update(Clothing objectToUpdate) throws SQLException {
		productDao.update(objectToUpdate);
		
		String sqlUpdateClothingStatement = "UPDATE Clothing SET [size] = ?, color = ? WHERE id = ?";
		PreparedStatement preparedUpdateClothingStatement = connectionDB.prepareStatement(sqlUpdateClothingStatement);
		preparedUpdateClothingStatement.setString(1, objectToUpdate.getSize());
		preparedUpdateClothingStatement.setString(2, objectToUpdate.getColor());
		preparedUpdateClothingStatement.setInt(3, objectToUpdate.getId());

		preparedUpdateClothingStatement.execute();
		
		return true;
	}

	@Override
	public boolean delete(Clothing objectToDelete) throws SQLException {
		productDao.delete(objectToDelete);

		String sqlDeleteClothingStatement = "DELETE FROM Product WHERE id = ?";
		PreparedStatement preparedDeleteClothingStatement = connectionDB.prepareStatement(sqlDeleteClothingStatement);
		preparedDeleteClothingStatement.setInt(1, objectToDelete.getId());
		preparedDeleteClothingStatement.execute();
		
		return true;
	}

}
