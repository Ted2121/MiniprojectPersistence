package data_access.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import model.Invoice;

public interface InvoiceDao {
	Invoice findByInvoiceNumber(String invoiceNo) throws SQLException;
	List<Invoice> findAll() throws SQLException;
	void create(Invoice objectToInsert) throws SQLException, ParseException;
	boolean update(Invoice objectToUpdate) throws SQLException;
	boolean delete(Invoice objectToDelete) throws SQLException;
	void setSalesOrderRelatedToThisInvoice(Invoice invoice) throws SQLException;
	
}