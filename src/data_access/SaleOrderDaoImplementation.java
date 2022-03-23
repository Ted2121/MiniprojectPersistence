package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import data_access.interfaces.SaleOrderDao;
import model.Customer;
import model.Invoice;
import model.SaleOrder;

public class SaleOrderDaoImplementation implements SaleOrderDao{

	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private List<SaleOrder> buildObjects(ResultSet rs) throws SQLException{
		List<SaleOrder> SaleOrderList = new ArrayList<SaleOrder>();
		while(rs.next()) {
			SaleOrderList.add(buildObject(rs));
		}
		
		return SaleOrderList;
	}
	
	private SaleOrder buildObject(ResultSet rs) throws SQLException{
		SaleOrder buildedObject = new SaleOrder(rs.getInt("id"),rs.getString("orderDate"),rs.getString("deliveryDate"),rs.getBoolean("deliveryStatus"),
				rs.getDouble("amount"));
		buildedObject.setFK_Invoice(rs.getString("FK_Invoice"));
		buildedObject.setFK_Customer(rs.getInt("FK_Customer"));
		return buildedObject;
	}

	@Override
	public SaleOrder findById(int id)  throws SQLException{
		String query = "SELECT * FROM SaleOrder WHERE id = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, id);
		ResultSet rs = preparedSelectStatement.executeQuery();
		SaleOrder retrievedSaleOrder = null;
		while(rs.next()) {
			retrievedSaleOrder = buildObject(rs);
		}
		
		return retrievedSaleOrder;
	}

	@Override
	public List<SaleOrder> findAll()  throws SQLException{
		String query = "SELECT * FROM SaleOrder";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<SaleOrder> retrievedSaleOrderList = buildObjects(rs);

		return retrievedSaleOrderList;
	}

	@Override
	public int create(SaleOrder objectToInsert)  throws SQLException{
		
		String sqlInsertSaleOrderStatement = "INSERT INTO SaleOrder([orderDate],amount,deliveryDate, deliveryStatus, FK_Invoice, FK_Customer)"
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement preparedInsertSaleOrderStatementWithGeneratedKey = connectionDB.prepareStatement(sqlInsertSaleOrderStatement, Statement.RETURN_GENERATED_KEYS);
		preparedInsertSaleOrderStatementWithGeneratedKey.setString(1, objectToInsert.getOrderDate());
		preparedInsertSaleOrderStatementWithGeneratedKey.setDouble(2, objectToInsert.getAmount());
		preparedInsertSaleOrderStatementWithGeneratedKey.setString(3, objectToInsert.getDeliveryDate());
		preparedInsertSaleOrderStatementWithGeneratedKey.setBoolean(4, objectToInsert.isDeliveryStatus());
		if(!objectToInsert.getFK_Invoice().equals(null)) {
			DaoFactory.getSaleOrderDao().setInvoiceRelatedToThisSaleOrder(objectToInsert);
			preparedInsertSaleOrderStatementWithGeneratedKey.setString(5, objectToInsert.getInvoice().getInvoiceNo());
		}else {
			preparedInsertSaleOrderStatementWithGeneratedKey.setNull(5, Types.NVARCHAR);
		}
		
		if(objectToInsert.getFK_Customer() != 0) {
			DaoFactory.getSaleOrderDao().setCustomerRelatedToThisSaleOrder(objectToInsert);
			preparedInsertSaleOrderStatementWithGeneratedKey.setInt(6, objectToInsert.getCustomer().getId());
		}else {
			preparedInsertSaleOrderStatementWithGeneratedKey.setNull(6, Types.INTEGER);
		}
		
		preparedInsertSaleOrderStatementWithGeneratedKey.executeUpdate();
		
		ResultSet tableContainingGenratedIds = preparedInsertSaleOrderStatementWithGeneratedKey.getGeneratedKeys();
		int generatedId = 0;
		while(tableContainingGenratedIds.next()) {
			generatedId = tableContainingGenratedIds.getInt(1);
		}
		objectToInsert.setId(generatedId);
		
		return generatedId;
	}

	@Override
	public boolean update(SaleOrder objectToUpdate)  throws SQLException{
		
		String sqlUpdateSaleOrderStatement = "UPDATE SaleOrder SET [orderDate] = ? ,amount = ? ,deliveryDate = ?, deliveryStatus = ?"
				+ ", FK_Invoice = ?, FK_Customer = ? WHERE id = ?";
		PreparedStatement preparedUpdateSaleOrderStatement = connectionDB.prepareStatement(sqlUpdateSaleOrderStatement);
		preparedUpdateSaleOrderStatement.setString(1, objectToUpdate.getOrderDate());
		preparedUpdateSaleOrderStatement.setDouble(2, objectToUpdate.getAmount());
		preparedUpdateSaleOrderStatement.setString(3, objectToUpdate.getDeliveryDate());
		preparedUpdateSaleOrderStatement.setBoolean(4, objectToUpdate.isDeliveryStatus());
		preparedUpdateSaleOrderStatement.setString(5, objectToUpdate.getInvoice().getInvoiceNo());
		preparedUpdateSaleOrderStatement.setInt(6, objectToUpdate.getCustomer().getId());
		preparedUpdateSaleOrderStatement.setInt(7, objectToUpdate.getId());

		preparedUpdateSaleOrderStatement.execute();
		
		return true;
	}

	@Override
	public boolean delete(SaleOrder objectToDelete)  throws SQLException{

		String sqlDeleteSaleOrderStatement = "DELETE FROM SaleOrder WHERE id = ?";
		PreparedStatement preparedDeleteSaleOrderStatement = connectionDB.prepareStatement(sqlDeleteSaleOrderStatement);
		preparedDeleteSaleOrderStatement.setInt(1, objectToDelete.getId());
		preparedDeleteSaleOrderStatement.execute();
		
		return true;
	}

	@Override
	public List<SaleOrder> findByCustomer(Customer customer) throws SQLException {
		String query = "SELECT * FROM SaleOrder WHERE FK_Customer = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, customer.getId());
		
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<SaleOrder> retrievedSaleOrderList = buildObjects(rs);

		return retrievedSaleOrderList;
	}
	
	@Override
	public SaleOrder findByInvoice(Invoice invoice) throws SQLException{
		String query = "SELECT * FROM SaleOrder WHERE FK_Invoice = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setString(1, invoice.getInvoiceNo());
		
		ResultSet rs = preparedSelectStatement.executeQuery();
		SaleOrder retrievedSaleOrderList = null;
		while(rs.next()) {
			retrievedSaleOrderList = buildObject(rs);
		}
		
		return retrievedSaleOrderList;
	}

	@Override
	public boolean setInvoiceRelatedToThisSaleOrder(SaleOrder saleOrder) throws SQLException {
		saleOrder.setInvoice(DaoFactory.getInvoiceDao().findByInvoiceNumber(saleOrder.getFK_Invoice()));
		return true;
	}

	@Override
	public boolean setCustomerRelatedToThisSaleOrder(SaleOrder saleOrder) throws SQLException {
		saleOrder.setCustomer(DaoFactory.getCustomerDao().findById(saleOrder.getFK_Customer()));
		return true;
	}

	@Override
	public boolean setLineItemRelatedToThisSaleOrder(SaleOrder saleOrder) throws SQLException {
		saleOrder.setLineItem(DaoFactory.getLineItemDao().findBySaleOrder(saleOrder));
		return true;
	}

}
