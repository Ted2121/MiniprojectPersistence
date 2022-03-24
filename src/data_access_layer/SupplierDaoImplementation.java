package data_access_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data_access_layer.data_access_interfaces.SupplierDao;
import model.Product;
import model.Supplier;

public class SupplierDaoImplementation implements SupplierDao{

	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private ArrayList<Supplier> buildObjects(ResultSet rs) throws SQLException{
		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		while(rs.next()) {
			supplierList.add(buildObject(rs));
		}
		
		return supplierList;
	}
	
	private Supplier buildObject(ResultSet rs) throws SQLException{
		Supplier buildedObject = new Supplier(rs.getInt("id"),rs.getString("name"),rs.getString("address"),rs.getString("country"),
				rs.getString("phoneNo"), rs.getString("email"));
		return buildedObject;
	}

	@Override
	public Supplier findSupplierById(int id) throws SQLException{
		String query = "SELECT * FROM Supplier WHERE id = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, id);
		ResultSet rs = preparedSelectStatement.executeQuery();
		Supplier retrievedSupplier = null;
		while(rs.next()) {
			retrievedSupplier = buildObject(rs);
		}
		
		return retrievedSupplier;
	}

	@Override
	public ArrayList<Supplier> findAllSuppliers()  throws SQLException{
		String query = "SELECT * FROM Supplier";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		ArrayList<Supplier> retrievedSupplierList = buildObjects(rs);

		return retrievedSupplierList;
	}

	@Override
	public int createSupplier(Supplier objectToInsert)  throws SQLException{
		
		String sqlInsertSupplierStatement = "INSERT INTO Supplier([name], [address], country, phoneno, email)"
				+ "VALUES(?, ?, ?, ?, ?);";
		PreparedStatement preparedInsertSupplierStatementWithGeneratedKey = connectionDB.prepareStatement(sqlInsertSupplierStatement, Statement.RETURN_GENERATED_KEYS);
		preparedInsertSupplierStatementWithGeneratedKey.setString(1, objectToInsert.getName());
		preparedInsertSupplierStatementWithGeneratedKey.setString(2, objectToInsert.getAddress());
		preparedInsertSupplierStatementWithGeneratedKey.setString(3, objectToInsert.getCountry());
		preparedInsertSupplierStatementWithGeneratedKey.setString(4, objectToInsert.getPhoneNo());
		preparedInsertSupplierStatementWithGeneratedKey.setString(5, objectToInsert.getEmail());

		
		preparedInsertSupplierStatementWithGeneratedKey.executeUpdate();
		
		ResultSet tableContainingGenratedIds = preparedInsertSupplierStatementWithGeneratedKey.getGeneratedKeys();
		int generatedId = 0;
		while(tableContainingGenratedIds.next()) {
			generatedId = tableContainingGenratedIds.getInt(1);
		}
		objectToInsert.setId(generatedId);
		
		return generatedId;
	}

	@Override
	public boolean updateSupplier(Supplier objectToUpdate)  throws SQLException{
		
		String sqlUpdateSupplierStatement = "UPDATE Supplier SET [name] = ?, [address] = ?, country = ?, phoneno = ?, email = ? WHERE id = ?";
		PreparedStatement preparedUpdateSupplierStatement = connectionDB.prepareStatement(sqlUpdateSupplierStatement);
		preparedUpdateSupplierStatement.setString(1, objectToUpdate.getName());
		preparedUpdateSupplierStatement.setString(2, objectToUpdate.getAddress());
		preparedUpdateSupplierStatement.setString(3, objectToUpdate.getCountry());
		preparedUpdateSupplierStatement.setString(4, objectToUpdate.getPhoneNo());
		preparedUpdateSupplierStatement.setString(5, objectToUpdate.getEmail());
		preparedUpdateSupplierStatement.setInt(6, objectToUpdate.getId());

		preparedUpdateSupplierStatement.execute();
		
		return true;
	}

	@Override
	public boolean deleteSupplier(Supplier objectToDelete)  throws SQLException{

		String sqlDeleteSupplierStatement = "DELETE FROM Supplier WHERE id = ?";
		PreparedStatement preparedDeleteSupplierStatement = connectionDB.prepareStatement(sqlDeleteSupplierStatement);
		preparedDeleteSupplierStatement.setInt(1, objectToDelete.getId());
		preparedDeleteSupplierStatement.execute();
		
		return true;
	}

	@Override
	public boolean setProductsRelatedToThisSupplier(Supplier supplier) throws SQLException {
		ArrayList<Product> productsLinkedToTheSupplier = new ArrayList<Product>();
		productsLinkedToTheSupplier.addAll(DaoFactory.getItemDao().findItemsBySupplier(supplier));
		productsLinkedToTheSupplier.addAll(DaoFactory.getClothingDao().findClothingsBySupplier(supplier));
		supplier.setProducts(productsLinkedToTheSupplier);
		return true;
	}
}
