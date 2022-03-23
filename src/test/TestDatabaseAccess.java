package test;

import static org.junit.Assert.*;

import org.junit.*;

import data_access.DatabaseConnection;


public class TestDatabaseAccess {
	
	
	DatabaseConnection con = null;

	@Before
	public void setUp() {
		con = DatabaseConnection.getInstance();
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);
		
		con = DatabaseConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);		
	}


}
