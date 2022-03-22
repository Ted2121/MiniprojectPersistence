package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data_access.interfaces.InvoiceDao;
import model.Invoice;

public class InvoiceDaoImplementation implements InvoiceDao{
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	
	private List<Invoice> buildObjects(ResultSet rs) throws SQLException{
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		while(rs.next()) {
			invoiceList.add(buildObject(rs));
		}
		
		return invoiceList;
	}
	
	private Invoice buildObject(ResultSet rs) throws SQLException{
		Invoice buildedObject = new Invoice(rs.getInt("id"),rs.getString("invoiceno"), rs.getString("paymentDate") , rs.getDouble("amount"));
		return buildedObject;
	}

	@Override
	public Invoice findById(int id) throws SQLException {
		String query = "SELECT * FROM Invoice WHERE id = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, id);
		ResultSet rs = preparedSelectStatement.executeQuery();	
		Invoice retrievedInvoice = null;
		while(rs.next()) {
			retrievedInvoice = buildObject(rs);
		}
		
		return retrievedInvoice;
	}

	@Override
	public List<Invoice> findAll() throws SQLException {
		String query = "SELECT * FROM Invoice";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<Invoice> retrievedInvoiceList = buildObjects(rs);

		return retrievedInvoiceList;
	}

	@Override
	public int create(Invoice objectToInsert) throws SQLException {
		String sqlInsertInvoiceStatement = "INSERT INTO Invoice(invoiceno, paymentDate, amount) VALUES(?, ?, ?);";
		PreparedStatement preparedInsertInvoiceStatementWithGeneratedKey = connectionDB.prepareStatement(sqlInsertInvoiceStatement, Statement.RETURN_GENERATED_KEYS);
		preparedInsertInvoiceStatementWithGeneratedKey.setString(1, objectToInsert.getInvoiceNo());
		preparedInsertInvoiceStatementWithGeneratedKey.setString(2, objectToInsert.getPaymentDate() );
		preparedInsertInvoiceStatementWithGeneratedKey.setDouble(3, objectToInsert.getAmount());
		
		preparedInsertInvoiceStatementWithGeneratedKey.executeUpdate();
		ResultSet tableContainingGenratedIds = preparedInsertInvoiceStatementWithGeneratedKey.getGeneratedKeys();
		int generatedId = 0;
		while(tableContainingGenratedIds.next()) {
			generatedId = tableContainingGenratedIds.getInt(1);
		}
		objectToInsert.setId(generatedId);
		
		return generatedId;
	}

	@Override
	public boolean update(Invoice objectToUpdate) throws SQLException {
		String sqlUpdateInvoiceStatement = "UPDATE Invoice SET invoiceno = ?, paymentDate = ?, amount= ? WHERE id = ?";
		PreparedStatement preparedUpdateInvoiceStatement = connectionDB.prepareStatement(sqlUpdateInvoiceStatement);
		preparedUpdateInvoiceStatement.setString(1, objectToUpdate.getInvoiceNo());
		preparedUpdateInvoiceStatement.setString(2, objectToUpdate.getPaymentDate());
		preparedUpdateInvoiceStatement.setDouble(3, objectToUpdate.getAmount());
		preparedUpdateInvoiceStatement.setInt(4, objectToUpdate.getId());

		preparedUpdateInvoiceStatement.execute();
		
		return true;
	}

	@Override
	public boolean delete(Invoice objectToDelete) throws SQLException {
		String sqlDeleteInvoiceStatement = "DELETE FROM Invoice WHERE id = ?";
		PreparedStatement preparedDeleteInvoiceStatement = connectionDB.prepareStatement(sqlDeleteInvoiceStatement);
		preparedDeleteInvoiceStatement.setInt(1, objectToDelete.getId());
		preparedDeleteInvoiceStatement.execute();
		
		return true;
	}

}
