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
	
	private List<Clothing> buildObjects(ResultSet rs) throws SQLException{
		List<Clothing> clothingList = new ArrayList<Clothing>();
		while(rs.next()) {
			clothingList.add(buildObject(rs));
		}
		
		return clothingList;
	}
	
	private Clothing buildObject(ResultSet rs) throws SQLException{
		
		Clothing buildedObject = new Clothing(rs.getInt("id"),rs.getString("name"), rs.getDouble("purchasePrice"), rs.getDouble("salesPrice"),
				rs.getString("countryOfOrigin"), rs.getInt("minStock"), rs.getString("size"), rs.getString("color"));
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
		String sqlInsertProductStatement = "INSERT INTO Product([name], purchasePrice, salesPrice, countryOfOrigin, minStock, stock, FK_Supplier)"
				+ "VALUES(? , ? , ? , ? , ? , ? , ?);";
		PreparedStatement preparedInsertProductStatementWithGeneratedKey = connectionDB.prepareStatement(sqlInsertProductStatement, Statement.RETURN_GENERATED_KEYS);
		preparedInsertProductStatementWithGeneratedKey.setString(1, objectToInsert.getName());
		preparedInsertProductStatementWithGeneratedKey.setDouble(2, objectToInsert.getPurchasePrice());
		preparedInsertProductStatementWithGeneratedKey.setDouble(3, objectToInsert.getSalePrice());
		preparedInsertProductStatementWithGeneratedKey.setString(4, objectToInsert.getCountryOfOrigin());
		preparedInsertProductStatementWithGeneratedKey.setInt(5, objectToInsert.getMinStock());
		preparedInsertProductStatementWithGeneratedKey.setInt(6, 0); //Hardcoded value TODO use product.stock
		preparedInsertProductStatementWithGeneratedKey.setInt(7, 1); //Hardcoded value TODO change it to the supplier id
		
		preparedInsertProductStatementWithGeneratedKey.executeUpdate();
		ResultSet tableContainingGenratedIds = preparedInsertProductStatementWithGeneratedKey.getGeneratedKeys();
		int generatedId = 0;
		while(tableContainingGenratedIds.next()) {
			generatedId = tableContainingGenratedIds.getInt(1);
		}
		
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
		String sqlUpdateProductStatement = "UPDATE Product SET [name] = ?, purchasePrice = ?, salesPrice = ?, countryOfOrigin = ?, minStock = ?, stock = ?, FK_Supplier = ? WHERE id = ?";
		PreparedStatement preparedUpdateProductStatement = connectionDB.prepareStatement(sqlUpdateProductStatement);
		preparedUpdateProductStatement.setString(1, objectToUpdate.getName());
		preparedUpdateProductStatement.setDouble(2, objectToUpdate.getPurchasePrice());
		preparedUpdateProductStatement.setDouble(3, objectToUpdate.getSalePrice());
		preparedUpdateProductStatement.setString(4, objectToUpdate.getCountryOfOrigin());
		preparedUpdateProductStatement.setInt(5, objectToUpdate.getMinStock());
		preparedUpdateProductStatement.setInt(6, 0); //Hardcoded value TODO use product.stock
		preparedUpdateProductStatement.setInt(7, 1); //Hardcoded value TODO change it to the supplier id
		preparedUpdateProductStatement.setInt(8, objectToUpdate.getId());
		
		preparedUpdateProductStatement.execute();
		
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

		String sqlDeleteClothingStatement = "DELETE FROM Clothing WHERE id = ?";
		PreparedStatement preparedDeleteClothingStatement = connectionDB.prepareStatement(sqlDeleteClothingStatement);
		preparedDeleteClothingStatement.setInt(1, objectToDelete.getId());
		preparedDeleteClothingStatement.execute();

		String sqlDeleteProductStatement = "DELETE FROM Product WHERE id = ?";
		PreparedStatement preparedDeleteProductStatement = connectionDB.prepareStatement(sqlDeleteProductStatement);
		preparedDeleteProductStatement.setInt(1, objectToDelete.getId());
		preparedDeleteProductStatement.execute();
		
		return true;
	}

}
