package data_access_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_access_layer.data_access_interfaces.InvoiceDao;
import model.Invoice;

public class InvoiceDaoImplementation implements InvoiceDao{
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	
	private ArrayList<Invoice> buildObjects(ResultSet rs) throws SQLException{
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		while(rs.next()) {
			invoiceList.add(buildObject(rs));
		}
		
		return invoiceList;
	}
	
	private Invoice buildObject(ResultSet rs) throws SQLException{
		Invoice buildedObject = new Invoice(rs.getString("invoiceno"), rs.getString("paymentDate") , rs.getDouble("amount"));
		return buildedObject;
	}

	@Override
	public Invoice findInvoiceByInvoiceNumber(String invoiceNo) throws SQLException {
		String query = "SELECT * FROM Invoice WHERE [invoiceno] = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setString(1, invoiceNo);
		ResultSet rs = preparedSelectStatement.executeQuery();	
		Invoice retrievedInvoice = null;
		while(rs.next()) {
			retrievedInvoice = buildObject(rs);
		}
		
		return retrievedInvoice;
	}

	@Override
	public ArrayList<Invoice> findAllInvoices() throws SQLException {
		String query = "SELECT * FROM Invoice";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		ArrayList<Invoice> retrievedInvoiceList = buildObjects(rs);

		return retrievedInvoiceList;
	}

	@Override
	public void createInvoice(Invoice objectToInsert) throws SQLException {
		String sqlInsertInvoiceStatement = "INSERT INTO Invoice(invoiceno, paymentDate, amount) VALUES(?, ?, ?);";
		PreparedStatement preparedInsertInvoiceStatementWithGeneratedKey = connectionDB.prepareStatement(sqlInsertInvoiceStatement);
		preparedInsertInvoiceStatementWithGeneratedKey.setString(1, objectToInsert.getInvoiceNo());
		preparedInsertInvoiceStatementWithGeneratedKey.setString(2, objectToInsert.getPaymentDate() );
		preparedInsertInvoiceStatementWithGeneratedKey.setDouble(3, objectToInsert.getAmount());
		
		preparedInsertInvoiceStatementWithGeneratedKey.execute();
		
	}

	@Override
	public boolean updateInvoice(Invoice objectToUpdate) throws SQLException {
		String sqlUpdateInvoiceStatement = "UPDATE Invoice SET paymentDate = ?, amount= ? WHERE invoiceno = ?";
		PreparedStatement preparedUpdateInvoiceStatement = connectionDB.prepareStatement(sqlUpdateInvoiceStatement);
		preparedUpdateInvoiceStatement.setString(1, objectToUpdate.getPaymentDate());
		preparedUpdateInvoiceStatement.setDouble(2, objectToUpdate.getAmount());
		preparedUpdateInvoiceStatement.setString(3, objectToUpdate.getInvoiceNo());

		preparedUpdateInvoiceStatement.execute();
		
		return true;
	}

	@Override
	public boolean deleteInvoice(Invoice objectToDelete) throws SQLException {
		String sqlDeleteInvoiceStatement = "DELETE FROM Invoice WHERE invoiceno = ?";
		PreparedStatement preparedDeleteInvoiceStatement = connectionDB.prepareStatement(sqlDeleteInvoiceStatement);
		preparedDeleteInvoiceStatement.setString(1, objectToDelete.getInvoiceNo());
		preparedDeleteInvoiceStatement.execute();
		
		return true;
	}

	@Override
	public void setSalesOrderRelatedToThisInvoice(Invoice invoice) throws SQLException {
		invoice.setSaleOrder(DaoFactory.getSaleOrderDao().findSaleOrderByInvoice(invoice));
		
	}

}
