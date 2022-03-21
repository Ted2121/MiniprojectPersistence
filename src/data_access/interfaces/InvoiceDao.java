package data_access.interfaces;

import java.util.List;

import model.Invoice;

public interface InvoiceDao {
	Invoice findById(int id);
	List<Invoice> findAll();
	int create(Invoice objectToInsert);
	void update(Invoice objectToUpdate);
	void delete(Invoice objectToDelete);
}
