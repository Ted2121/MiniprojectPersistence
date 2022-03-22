package data_access.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import model.Invoice;

public interface InvoiceDao {
	Invoice findById(int id) throws SQLException;
	List<Invoice> findAll() throws SQLException;
	int create(Invoice objectToInsert) throws SQLException, ParseException;
	boolean update(Invoice objectToUpdate) throws SQLException;
	boolean delete(Invoice objectToDelete) throws SQLException;
}
