package data_access;

import java.util.List;

import model.Invoice;

public interface IF_InvoiceDao {
	Invoice findById(int id);
	List<Invoice> findAll();
	int create(Invoice objectToInsert);
	void update(Invoice objectToUpdate);
	void delete(Invoice objectToDelete);
}
