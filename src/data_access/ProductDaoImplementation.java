package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data_access.interfaces.ProductDao;
import model.Product;

public class ProductDaoImplementation implements ProductDao {
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();

	@Override
	public int create(Product objectToInsert) throws SQLException {
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
		return generatedId;
	}

	@Override
	public void update(Product objectToUpdate) throws SQLException {
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
	}

	@Override
	public void delete(Product objectToDelete) throws SQLException {
		String sqlDeleteClothingStatement = "DELETE FROM Clothing WHERE id = ?";
		PreparedStatement preparedDeleteClothingStatement = connectionDB.prepareStatement(sqlDeleteClothingStatement);
		preparedDeleteClothingStatement.setInt(1, objectToDelete.getId());
		preparedDeleteClothingStatement.execute();
	}

}
