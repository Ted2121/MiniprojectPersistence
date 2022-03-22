package test;

import static org.junit.Assert.*;

import org.junit.*;

import data_access.DatabaseConnection;


//import controllayer.ControlPayStation;
//import controllayer.Currency;
//import controllayer.IPayStation;
//import controllayer.IReceipt;
//import controllayer.IllegalCoinException;


//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {
	
	
	DatabaseConnection con = null;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		con = DatabaseConnection.getInstance();
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);
		
//		DatabaseConnection.closeConnection();
//		boolean wasNullified = DatabaseConnection.instanceIsNull();
//		assertTrue("Disconnected - instance set to null", wasNullified);
		
		con = DatabaseConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);		
	}
	
	
//	/** Fixture for pay station testing. */
//	@After
//	public void cleanUp() {
//		DatabaseConnection.closeConnection();
//	}	
	

}
