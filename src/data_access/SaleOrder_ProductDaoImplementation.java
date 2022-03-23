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
import data_access.interfaces.SaleOrder_ProductDao;
import model.Clothing;

import model.*;

public class SaleOrder_ProductDaoImplementation implements SaleOrder_ProductDao{

	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private List<SaleOrder_Product> buildObjects(ResultSet rs) throws SQLException{
		List<SaleOrder_Product> SaleOrder_ProductList = new ArrayList<SaleOrder_Product>();
		while(rs.next()) {
			SaleOrder_ProductList.add(buildObject(rs));
		}
		
		return SaleOrder_ProductList;
	}
	
	private SaleOrder_Product buildObject(ResultSet rs) throws SQLException{
		ItemDao itemDao = DaoFactory.getItemDao();
		ClothingDao clothingDao = DaoFactory.getClothingDao();
		SaleOrderDao saleOrderDao = DaoFactory.getSaleOrderDao();
		
		Item item = itemDao.findById(rs.getInt("PK_FK_Product"));
		Clothing clothing = clothingDao.findById(rs.getInt("PK_FK_Product"));
		SaleOrder saleOrder = saleOrderDao.findById(rs.getInt("PK_FK_SaleOrder"));
		SaleOrder_Product buildedObject= null;
		if(item != null) {
			buildedObject = new SaleOrder_Product(item, saleOrder, rs.getInt("quantity"));
		}else {
			buildedObject = new SaleOrder_Product(clothing, saleOrder, rs.getInt("quantity"));
		}
		return buildedObject;
	}


	@Override
	public List<SaleOrder_Product> findBySaleOrder(SaleOrder saleOrder)  throws SQLException{
		String query = "SELECT * FROM SaleOrder_Product WHERE PK_FK_SaleOrder = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, saleOrder.getId());
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<SaleOrder_Product> retrievedSaleOrder_Product = null;
		retrievedSaleOrder_Product = buildObjects(rs);
		
		return retrievedSaleOrder_Product;
	}

	@Override
	public List<SaleOrder_Product> findByProduct(Product product) throws SQLException {
		String query = "SELECT * FROM SaleOrder_Product WHERE PK_FK_Product = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, product.getId());
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<SaleOrder_Product> retrievedSaleOrder_Product = null;
		while(rs.next()) {
			retrievedSaleOrder_Product = buildObjects(rs);
		}
		
		return retrievedSaleOrder_Product;
	}
	
	@Override
	public SaleOrder_Product findById(int idSaleOrder, int idProduct) throws SQLException {
		String query = "SELECT * FROM SaleOrder_Product WHERE PK_FK_SaleOrder = ? AND PK_FK_Product = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, idSaleOrder);
		preparedSelectStatement.setLong(2, idProduct);
		ResultSet rs = preparedSelectStatement.executeQuery();
		SaleOrder_Product retrievedSaleOrder_Product =null;
		while(rs.next()) {
			retrievedSaleOrder_Product = buildObject(rs);
		}
		
		return retrievedSaleOrder_Product;
	}

	@Override
	public List<SaleOrder_Product> findAll()  throws SQLException{
		String query = "SELECT * FROM SaleOrder_Product";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<SaleOrder_Product> retrievedSaleOrder_ProductList = buildObjects(rs);

		return retrievedSaleOrder_ProductList;
	}

	@Override
	public boolean create(SaleOrder_Product objectToInsert)  throws SQLException{
		
		String sqlInsertSaleOrder_ProductStatement = "INSERT INTO SaleOrder_Product(PK_FK_Product, PK_FK_SaleOrder, quantity) "
				+ "VALUES (?,?,?)";
		PreparedStatement preparedInsertSaleOrder_ProductStatement = connectionDB.prepareStatement(sqlInsertSaleOrder_ProductStatement);
		preparedInsertSaleOrder_ProductStatement.setInt(1, objectToInsert.getProduct().getId());
		preparedInsertSaleOrder_ProductStatement.setInt(2, objectToInsert.getOrder().getId());
		preparedInsertSaleOrder_ProductStatement.setInt(3, objectToInsert.getQuantity());
		preparedInsertSaleOrder_ProductStatement.executeUpdate();
		return true;
	}

	@Override
	public boolean update(SaleOrder_Product objectToUpdate)  throws SQLException{
		
		String sqlUpdateSaleOrder_ProductStatement = "UPDATE SaleOrder_Product SET quantity = ?"
				+ " WHERE PK_FK_Product = ? AND PK_FK_SaleOrder = ?";
		PreparedStatement preparedUpdateSaleOrder_ProductStatement = connectionDB.prepareStatement(sqlUpdateSaleOrder_ProductStatement);
		preparedUpdateSaleOrder_ProductStatement.setInt(1, objectToUpdate.getQuantity());
		preparedUpdateSaleOrder_ProductStatement.setInt(2, objectToUpdate.getProduct().getId());
		preparedUpdateSaleOrder_ProductStatement.setInt(3, objectToUpdate.getOrder().getId());

		preparedUpdateSaleOrder_ProductStatement.execute();
		
		return true;
	}

	@Override
	public boolean delete(SaleOrder_Product objectToDelete)  throws SQLException{

		String sqlDeleteSaleOrder_ProductStatement = "DELETE FROM SaleOrder_Product WHERE PK_FK_Product = ? AND PK_FK_SaleOrder = ?";
		PreparedStatement preparedDeleteSaleOrder_ProductStatement = connectionDB.prepareStatement(sqlDeleteSaleOrder_ProductStatement);
		preparedDeleteSaleOrder_ProductStatement.setInt(1, objectToDelete.getProduct().getId());
		preparedDeleteSaleOrder_ProductStatement.setInt(2, objectToDelete.getOrder().getId());
		preparedDeleteSaleOrder_ProductStatement.execute();
		
		return true;
	}

	
}
