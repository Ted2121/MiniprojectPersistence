package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_access.interfaces.ItemDao;
import model.Item;
import model.Item;
import model.Item;

public class ItemDaoImplementation implements ItemDao{
	Connection connectionDB = DatabaseConnection.getInstance().getDBcon();
	ProductDaoImplementation productDao = DaoFactory.getProductDao();
	
	private List<Item> buildObjects(ResultSet rs) throws SQLException{
		List<Item> itemList = new ArrayList<Item>();
		while(rs.next()) {
			itemList.add(buildObject(rs));
		}
		
		return itemList;
	}
	
	private Item buildObject(ResultSet rs) throws SQLException{
		Item buildedObject = new Item(rs.getInt("id"),rs.getString("name"), rs.getDouble("purchasePrice"), rs.getDouble("salesPrice"),
				rs.getString("countryOfOrigin"), rs.getInt("minStock"), rs.getInt("stock"), rs.getString("type"), rs.getString("description"));
		return buildedObject;
	}

	@Override
	public Item findById(int id)  throws SQLException{
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
	public List<Item> findAll()  throws SQLException{
		String query = "SELECT * FROM Item INNER JOIN Product ON Item.id = Product.id";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<Item> retrievedItemList = buildObjects(rs);

		return retrievedItemList;
	}

	@Override
	public List<Item> findByType(String type)  throws SQLException{
		String query = "SELECT * FROM Item INNER JOIN Product ON Item.id = Product.id WHERE Item.type = ?";
		PreparedStatement preparedSelectStatement = connectionDB.prepareStatement(query);
		preparedSelectStatement.setString(1, type);
		ResultSet rs = preparedSelectStatement.executeQuery();
		List<Item> itemList = new ArrayList<Item>();
		while(rs.next()) {
			itemList.add(buildObject(rs));
		}
		
		return itemList;
	}

	@Override
	public int create(Item objectToInsert)  throws SQLException{
		int generatedId = productDao.create(objectToInsert);
		
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
	public boolean update(Item objectToUpdate)  throws SQLException{
		productDao.update(objectToUpdate);
		
		String sqlUpdateItemStatement = "UPDATE Item SET [type] = ?, description = ? WHERE id = ?";
		PreparedStatement preparedUpdateItemStatement = connectionDB.prepareStatement(sqlUpdateItemStatement);
		preparedUpdateItemStatement.setString(1, objectToUpdate.getType());
		preparedUpdateItemStatement.setString(2, objectToUpdate.getDescription());
		preparedUpdateItemStatement.setInt(3, objectToUpdate.getId());

		preparedUpdateItemStatement.execute();
		
		return true;
	}

	@Override
	public boolean delete(Item objectToDelete)  throws SQLException{
		productDao.delete(objectToDelete);

		String sqlDeleteItemStatement = "DELETE FROM Item WHERE id = ?";
		PreparedStatement preparedDeleteItemStatement = connectionDB.prepareStatement(sqlDeleteItemStatement);
		preparedDeleteItemStatement.setInt(1, objectToDelete.getId());
		preparedDeleteItemStatement.execute();
		
		return true;
	}

}
