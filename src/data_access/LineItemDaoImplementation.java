package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_access.interfaces.ClothingDao;
import data_access.interfaces.ItemDao;
import data_access.interfaces.SaleOrderDao;
import data_access.interfaces.LineItemDao;
import model.Clothing;

import model.*;

public class LineItemDaoImplementation implements LineItemDao{

	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private List<LineItem> buildObjects(ResultSet rs) throws SQLException{
		List<LineItem> LineItemList = new ArrayList<LineItem>();
		while(rs.next()) {
			LineItemList.add(buildObject(rs));
		}
		
		return LineItemList;
	}
	
	private LineItem buildObject(ResultSet rs) throws SQLException{
		ItemDao itemDao = DaoFactory.getItemDao();
		ClothingDao clothingDao = DaoFactory.getClothingDao();
		SaleOrderDao saleOrderDao = DaoFactory.getSaleOrderDao();
		
		Item item = itemDao.findById(rs.getInt("PK_FK_Product"));
		Clothing clothing = clothingDao.findById(rs.getInt("PK_FK_Product"));
		SaleOrder saleOrder = saleOrderDao.findById(rs.getInt("PK_FK_SaleOrder"));
		LineItem buildedObject= null;
		if(item != null) {
			buildedObject = new LineItem(item, saleOrder, rs.getInt("quantity"));
		}else {
			buildedObject = new LineItem(clothing, saleOrder, rs.getInt("quantity"));
		}
		return buildedObject;
	}


	@Override
	public List<LineItem> findBySaleOrder(SaleOrder saleOrder)  throws SQLException{
		String query = "SELECT * FROM LineItem WHERE PK_FK_SaleOrder = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, saleOrder.getId());
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<LineItem> retrievedLineItem = null;
		retrievedLineItem = buildObjects(rs);
		
		return retrievedLineItem;
	}

	@Override
	public List<LineItem> findByProduct(Product product) throws SQLException {
		String query = "SELECT * FROM LineItem WHERE PK_FK_Product = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, product.getId());
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<LineItem> retrievedLineItem = null;
		retrievedLineItem = buildObjects(rs);
		
		return retrievedLineItem;
	}
	
	@Override
	public LineItem findById(int idSaleOrder, int idProduct) throws SQLException {
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
	public List<LineItem> findAll()  throws SQLException{
		String query = "SELECT * FROM LineItem";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<LineItem> retrievedLineItemList = buildObjects(rs);

		return retrievedLineItemList;
	}

	@Override
	public boolean create(LineItem objectToInsert)  throws SQLException{
		
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
	public boolean update(LineItem objectToUpdate)  throws SQLException{
		
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
	public boolean delete(LineItem objectToDelete)  throws SQLException{

		String sqlDeleteLineItemStatement = "DELETE FROM LineItem WHERE PK_FK_Product = ? AND PK_FK_SaleOrder = ?";
		PreparedStatement preparedDeleteLineItemStatement = connectionDB.prepareStatement(sqlDeleteLineItemStatement);
		preparedDeleteLineItemStatement.setInt(1, objectToDelete.getProduct().getId());
		preparedDeleteLineItemStatement.setInt(2, objectToDelete.getOrder().getId());
		preparedDeleteLineItemStatement.execute();
		
		return true;
	}

	
}
