package data_access_layer.data_access_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.SaleOrder;
import model.LineItem;

public interface LineItemDao {
	LineItem findLineItemById(int idSaleOrder, int idProduct) throws SQLException;
	ArrayList<LineItem> findLineItemsBySaleOrder(SaleOrder saleOrder) throws SQLException;
	ArrayList<LineItem> findLineItemsByProduct(Product product) throws SQLException;
	ArrayList<LineItem> findAllLineItems() throws SQLException; 
	boolean createLineItem(LineItem objectToInsert) throws SQLException;
	boolean updateLineItem(LineItem objectToUpdate) throws SQLException;
	boolean deleteLineItem(LineItem objectToDelete) throws SQLException;
}
