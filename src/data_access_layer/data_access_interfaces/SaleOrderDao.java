package data_access_layer.data_access_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Invoice;
import model.SaleOrder;

public interface SaleOrderDao {
	SaleOrder findSaleOrderById(int id) throws SQLException;
	ArrayList<SaleOrder> findAllSaleOrders() throws SQLException;
	ArrayList<SaleOrder> findSaleOrdersByCustomer(Customer customer) throws SQLException;
	SaleOrder findSaleOrderByInvoice(Invoice invoice) throws SQLException;
	int createSaleOrder(SaleOrder objectToInsert) throws SQLException;
	boolean updateSaleOrder(SaleOrder objectToUpdate) throws SQLException;
	boolean deleteSaleOrder(SaleOrder objectToDelete) throws SQLException;
	boolean setInvoiceRelatedToThisSaleOrder(SaleOrder saleOrder) throws SQLException;
	boolean setCustomerRelatedToThisSaleOrder(SaleOrder saleOrder) throws SQLException;
	boolean setLineItemRelatedToThisSaleOrder(SaleOrder saleOrder) throws SQLException;
	
}
