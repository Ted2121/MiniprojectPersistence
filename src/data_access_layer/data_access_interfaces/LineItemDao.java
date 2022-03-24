package data_access.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.SaleOrder;
import model.LineItem;

public interface LineItemDao {
	LineItem findById(int idSaleOrder, int idProduct) throws SQLException;
	List<LineItem> findBySaleOrder(SaleOrder saleOrder) throws SQLException;
	List<LineItem> findByProduct(Product product) throws SQLException;
	List<LineItem> findAll() throws SQLException; 
	boolean create(LineItem objectToInsert) throws SQLException;
	boolean update(LineItem objectToUpdate) throws SQLException;
	boolean delete(LineItem objectToDelete) throws SQLException;
}
