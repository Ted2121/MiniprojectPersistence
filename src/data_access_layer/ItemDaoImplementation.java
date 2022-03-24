package data_access_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_access_layer.data_access_interfaces.ItemDao;
import model.Item;

public class ItemDaoImplementation implements ItemDao{
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private ArrayList<Item> buildObjects(ResultSet rs) throws SQLException{
		ArrayList<Item> itemList = new ArrayList<Item>();
		while(rs.next()) {
			itemList.add(buildObject(rs));
		}
		
		return itemList;
	}
	
	private Item buildObject(ResultSet rs) throws SQLException{
		Item buildedObject = new Item(rs.getInt("id"),rs.getString("name"), rs.getDouble("purchasePrice"), rs.getDouble("salesPrice"),
				rs.getString("countryOfOrigin"), rs.getInt("minStock"), rs.getInt("stock"), rs.getString("type"), rs.getString("description"), rs.getInt("FK_Supplier"));
		buildedObject.setFK_Supplier(rs.getInt("FK_Supplier"));
		return buildedObject;
	}

	@Override
	public Item findItemById(int id)  throws SQLException{
		String query = "SELECT * FROM Item INNER JOIN Product ON Item.id = Product.id WHERE Item.id = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setLong(1, id);
		ResultSet rs = preparedSelectStatement.executeQuery();
		Item retrievedItem = null;
		while(rs.next()) {
			retrievedItem = buildObject(rs);
		}
		
		return retrievedItem;
	}

	@Override
	public ArrayList<Item> findAllItems()  throws SQLException{
		String query = "SELECT * FROM Item INNER JOIN Product ON Item.id = Product.id";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		ArrayList<Item> retrievedItemList = buildObjects(rs);

		return retrievedItemList;
	}

	@Override
	public ArrayList<Item> findItemsByType(String type)  throws SQLException{
		String query = "SELECT * FROM Item INNER JOIN Product ON Item.id = Product.id WHERE Item.type = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setString(1, type);
		ResultSet rs = preparedSelectStatement.executeQuery();
		ArrayList<Item> itemList = new ArrayList<Item>();
		while(rs.next()) {
			itemList.add(buildObject(rs));
		}
		
		return itemList;
	}

	@Override
	public int createItem(Item objectToInsert)  throws SQLException{
		int generatedId = productDao.createProduct(objectToInsert);
		
		String sqlInsertItemStatement = "INSERT INTO Item([type], description, id)"
				+ "VALUES(?, ?, ?);";
		PreparedStatement preparedInsertItemStatement = connectionDB.prepareStatement(sqlInsertItemStatement);
		preparedInsertItemStatement.setString(1, objectToInsert.getType());
		preparedInsertItemStatement.setString(2, objectToInsert.getDescription());
		preparedInsertItemStatement.setInt(3, generatedId);
		
		preparedInsertItemStatement.execute();
		
		objectToInsert.setId(generatedId);
		
		return generatedId;
	}

	@Override
	public boolean updateItem(Item objectToUpdate)  throws SQLException{
		productDao.updateProduct(objectToUpdate);
		
		String sqlUpdateItemStatement = "UPDATE Item SET [type] = ?, description = ? WHERE id = ?";
		PreparedStatement preparedUpdateItemStatement = connectionDB.prepareStatement(sqlUpdateItemStatement);
		preparedUpdateItemStatement.setString(1, objectToUpdate.getType());
		preparedUpdateItemStatement.setString(2, objectToUpdate.getDescription());
		preparedUpdateItemStatement.setInt(3, objectToUpdate.getId());

		preparedUpdateItemStatement.execute();
		return true;
	}

	@Override
	public boolean deleteItem(Item objectToDelete)  throws SQLException{
		productDao.deleteProduct(objectToDelete);

		String sqlDeleteItemStatement = "DELETE FROM Item WHERE id = ?";
		PreparedStatement preparedDeleteItemStatement = connectionDB.prepareStatement(sqlDeleteItemStatement);
		preparedDeleteItemStatement.setInt(1, objectToDelete.getId());
		preparedDeleteItemStatement.execute();
		return true;
	}

	@Override
	public void setSupplierRelatedToThisItem(Item item) throws SQLException {
		DaoFactory.getProductDao().setSupplierRelatedToThisProduct(item);
		
	}

	@Override
	public void setLineItemRelatedToThisItem(Item item) throws SQLException {
		DaoFactory.getProductDao().setLineItemRelatedToThisProduct(item);
		
	}

}
