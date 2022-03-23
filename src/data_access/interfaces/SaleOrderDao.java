package data_access.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Customer;
import model.Invoice;
import model.SaleOrder;

public interface SaleOrderDao {
	SaleOrder findById(int id) throws SQLException;
	List<SaleOrder> findAll() throws SQLException;
	List<SaleOrder> findByCustomer(Customer customer) throws SQLException;
	int create(SaleOrder objectToInsert) throws SQLException;
	boolean update(SaleOrder objectToUpdate) throws SQLException;
	boolean delete(SaleOrder objectToDelete) throws SQLException;
	boolean setInvoiceRelatedToThisSaleOrder(SaleOrder saleOrder) throws SQLException;
	boolean setCustomerRelatedToThisSaleOrder(SaleOrder saleOrder) throws SQLException;
	boolean setLineItemRelatedToThisSaleOrder(SaleOrder saleOrder) throws SQLException;
	SaleOrder findByInvoice(Invoice invoice) throws SQLException;
}
