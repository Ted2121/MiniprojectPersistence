package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data_access.interfaces.CustomerDao;
import model.Customer;

public class CustomerDaoImplementation implements CustomerDao{

	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private List<Customer> buildObjects(ResultSet rs) throws SQLException{
		List<Customer> customerList = new ArrayList<Customer>();
		while(rs.next()) {
			customerList.add(buildObject(rs));
		}
		
		return customerList;
	}
	
	private Customer buildObject(ResultSet rs) throws SQLException{
		
		Customer buildedObject = new Customer(rs.getInt("id"),rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getInt("zipcode"),
				rs.getString("phoneno"), rs.getString("type"));
		return buildedObject;
	}

	@Override
	public Customer findById(int id) throws SQLException {
		String query = "SELECT * FROM Customer WHERE id = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, id);
		ResultSet rs = preparedSelectStatement.executeQuery();	
		Customer retrievedCustomer = null;
		while(rs.next()) {
			retrievedCustomer = buildObject(rs);
		}
		
		return retrievedCustomer;
	}

	@Override
	public List<Customer> findAll() throws SQLException {
		String query = "SELECT * FROM Customer";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<Customer> retrievedClothingList = buildObjects(rs);

		return retrievedClothingList;
	}

	@Override
	public int create(Customer objectToInsert) throws SQLException {
		
		String sqlInsertCustomerStatement = "INSERT INTO Customer([name], [address], city, zipcode, phoneno, [type])"
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement preparedInsertCustomerStatementWithGeneratedKey = connectionDB.prepareStatement(sqlInsertCustomerStatement, Statement.RETURN_GENERATED_KEYS);
		preparedInsertCustomerStatementWithGeneratedKey.setString(1, objectToInsert.getName());
		preparedInsertCustomerStatementWithGeneratedKey.setString(2, objectToInsert.getAddress());
		preparedInsertCustomerStatementWithGeneratedKey.setString(3, objectToInsert.getCity());
		preparedInsertCustomerStatementWithGeneratedKey.setInt(4, objectToInsert.getZipcode());
		preparedInsertCustomerStatementWithGeneratedKey.setString(5, objectToInsert.getPhoneNo());
		preparedInsertCustomerStatementWithGeneratedKey.setString(6, objectToInsert.getType());
		
		preparedInsertCustomerStatementWithGeneratedKey.executeUpdate();
		ResultSet tableContainingGenratedIds = preparedInsertCustomerStatementWithGeneratedKey.getGeneratedKeys();
		int generatedId = 0;
		while(tableContainingGenratedIds.next()) {
			generatedId = tableContainingGenratedIds.getInt(1);
		}
		objectToInsert.setId(generatedId);
		
		return generatedId;
	}

	@Override
	public boolean update(Customer objectToUpdate) throws SQLException {
		
		String sqlUpdateCustomerStatement = "UPDATE Customer SET [name]= ?, [address]= ?, city= ?, zipcode= ?, phoneno= ?, [type]= ? WHERE id = ?";
		PreparedStatement preparedUpdateCustomerStatement = connectionDB.prepareStatement(sqlUpdateCustomerStatement);
		preparedUpdateCustomerStatement.setString(1, objectToUpdate.getName());
		preparedUpdateCustomerStatement.setString(2, objectToUpdate.getAddress());
		preparedUpdateCustomerStatement.setString(3, objectToUpdate.getCity());
		preparedUpdateCustomerStatement.setInt(4, objectToUpdate.getZipcode());
		preparedUpdateCustomerStatement.setString(5, objectToUpdate.getPhoneNo());
		preparedUpdateCustomerStatement.setString(6, objectToUpdate.getType());
		preparedUpdateCustomerStatement.setInt(7, objectToUpdate.getId());

		preparedUpdateCustomerStatement.execute();
		
		return true;
	}

	@Override
	public boolean delete(Customer objectToDelete) throws SQLException {
		
		String sqlDeleteProductStatement = "DELETE FROM Customer WHERE id = ?";
		PreparedStatement preparedDeleteProductStatement = connectionDB.prepareStatement(sqlDeleteProductStatement);
		preparedDeleteProductStatement.setInt(1, objectToDelete.getId());
		preparedDeleteProductStatement.execute();
		
		return true;
	}

	@Override
	public void setSalesOrderRelatedToThisCustomer(Customer customer) throws SQLException {
		customer.setSaleOrders(DaoFactory.getSaleOrderDao().findByCustomer(customer));
		
	}

}
