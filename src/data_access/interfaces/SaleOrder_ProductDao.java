package data_access.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.SaleOrder;
import model.SaleOrder_Product;

public interface SaleOrder_ProductDao {
	SaleOrder_Product findById(int idSaleOrder, int idProduct) throws SQLException;
	List<SaleOrder_Product> findBySaleOrder(SaleOrder saleOrder) throws SQLException;
	List<SaleOrder_Product> findByProduct(Product product) throws SQLException;
	List<SaleOrder_Product> findAll() throws SQLException; 
	boolean create(SaleOrder_Product objectToInsert) throws SQLException;
	boolean update(SaleOrder_Product objectToUpdate) throws SQLException;
	boolean delete(SaleOrder_Product objectToDelete) throws SQLException;
}
