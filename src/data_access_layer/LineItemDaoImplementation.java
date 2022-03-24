package data_access_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_access_layer.data_access_interfaces.ClothingDao;
import data_access_layer.data_access_interfaces.ItemDao;
import data_access_layer.data_access_interfaces.LineItemDao;
import data_access_layer.data_access_interfaces.SaleOrderDao;
import model.Clothing;

import model.*;

public class LineItemDaoImplementation implements LineItemDao{

	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private ArrayList<LineItem> buildObjects(ResultSet rs) throws SQLException{
		ArrayList<LineItem> LineItemList = new ArrayList<LineItem>();
		while(rs.next()) {
			LineItemList.add(buildObject(rs));
		}
		
		return LineItemList;
	}
	
	private LineItem buildObject(ResultSet rs) throws SQLException{
		ItemDao itemDao = DaoFactory.getItemDao();
		ClothingDao clothingDao = DaoFactory.getClothingDao();
		SaleOrderDao saleOrderDao = DaoFactory.getSaleOrderDao();
		
		Item item = itemDao.findItemById(rs.getInt("PK_FK_Product"));
		Clothing clothing = clothingDao.findClothingById(rs.getInt("PK_FK_Product"));
		SaleOrder saleOrder = saleOrderDao.findSaleOrderById(rs.getInt("PK_FK_SaleOrder"));
		LineItem buildedObject= null;
		if(item != null) {
			buildedObject = new LineItem(item, saleOrder, rs.getInt("quantity"));
		}else {
			buildedObject = new LineItem(clothing, saleOrder, rs.getInt("quantity"));
		}
		return buildedObject;
	}


	@Override
	public ArrayList<LineItem> findLineItemsBySaleOrder(SaleOrder saleOrder)  throws SQLException{
		String query = "SELECT * FROM LineItem WHERE PK_FK_SaleOrder = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, saleOrder.getId());
		ResultSet rs = preparedSelectStatement.executeQuery();
		ArrayList<LineItem> retrievedLineItem = null;
		retrievedLineItem = buildObjects(rs);
		
		return retrievedLineItem;
	}

	@Override
	public ArrayList<LineItem> findLineItemsByProduct(Product product) throws SQLException {
		String query = "SELECT * FROM LineItem WHERE PK_FK_Product = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, product.getId());
		ResultSet rs = preparedSelectStatement.executeQuery();
		ArrayList<LineItem> retrievedLineItem = null;
		retrievedLineItem = buildObjects(rs);
		
		return retrievedLineItem;
	}
	
	@Override
	public LineItem findLineItemById(int idSaleOrder, int idProduct) throws SQLException {
		String query = "SELECT * FROM LineItem WHERE PK_FK_SaleOrder = ? AND PK_FK_Product = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, idSaleOrder);
		preparedSelectStatement.setLong(2, idProduct);
		ResultSet rs = preparedSelectStatement.executeQuery();
		LineItem retrievedLineItem =null;
		while(rs.next()) {
			retrievedLineItem = buildObject(rs);
		}
		
		return retrievedLineItem;
	}

	@Override
	public ArrayList<LineItem> findAllLineItems()  throws SQLException{
		String query = "SELECT * FROM LineItem";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		ArrayList<LineItem> retrievedLineItemList = buildObjects(rs);

		return retrievedLineItemList;
	}

	@Override
	public boolean createLineItem(LineItem objectToInsert)  throws SQLException{
		
		String sqlInsertLineItemStatement = "INSERT INTO LineItem(PK_FK_Product, PK_FK_SaleOrder, quantity) "
				+ "VALUES (?,?,?)";
		PreparedStatement preparedInsertLineItemStatement = connectionDB.prepareStatement(sqlInsertLineItemStatement);
		preparedInsertLineItemStatement.setInt(1, objectToInsert.getProduct().getId());
		preparedInsertLineItemStatement.setInt(2, objectToInsert.getOrder().getId());
		preparedInsertLineItemStatement.setInt(3, objectToInsert.getQuantity());
		preparedInsertLineItemStatement.executeUpdate();
		return true;
	}

	@Override
	public boolean updateLineItem(LineItem objectToUpdate)  throws SQLException{
		
		String sqlUpdateLineItemStatement = "UPDATE LineItem SET quantity = ?"
				+ " WHERE PK_FK_Product = ? AND PK_FK_SaleOrder = ?";
		PreparedStatement preparedUpdateLineItemStatement = connectionDB.prepareStatement(sqlUpdateLineItemStatement);
		preparedUpdateLineItemStatement.setInt(1, objectToUpdate.getQuantity());
		preparedUpdateLineItemStatement.setInt(2, objectToUpdate.getProduct().getId());
		preparedUpdateLineItemStatement.setInt(3, objectToUpdate.getOrder().getId());

		preparedUpdateLineItemStatement.execute();
		
		return true;
	}

	@Override
	public boolean deleteLineItem(LineItem objectToDelete)  throws SQLException{

		String sqlDeleteLineItemStatement = "DELETE FROM LineItem WHERE PK_FK_Product = ? AND PK_FK_SaleOrder = ?";
		PreparedStatement preparedDeleteLineItemStatement = connectionDB.prepareStatement(sqlDeleteLineItemStatement);
		preparedDeleteLineItemStatement.setInt(1, objectToDelete.getProduct().getId());
		preparedDeleteLineItemStatement.setInt(2, objectToDelete.getOrder().getId());
		preparedDeleteLineItemStatement.execute();
		
		return true;
	}

	
}
