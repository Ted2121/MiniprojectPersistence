package data_access_layer.data_access_interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Invoice;

public interface InvoiceDao {
	Invoice findInvoiceByInvoiceNumber(String invoiceNo) throws SQLException;
	ArrayList<Invoice> findAllInvoices() throws SQLException;
	void createInvoice(Invoice objectToInsert) throws SQLException, ParseException;
	boolean updateInvoice(Invoice objectToUpdate) throws SQLException;
	boolean deleteInvoice(Invoice objectToDelete) throws SQLException;
	void setSalesOrderRelatedToThisInvoice(Invoice invoice) throws SQLException;
	
}
