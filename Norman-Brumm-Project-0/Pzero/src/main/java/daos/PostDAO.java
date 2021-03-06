package daos;

import java.sql.Connection;
import java.sql.SQLException;

import sqlCommands.SQLInsert;
import tools.CheckWrongData;

public class PostDAO {
	Connection conn;
	private final static String CUSTOMERS = "customers";
	
	public PostDAO(Connection conn) {
		this.conn = conn;
	}
	
	/** Creates a new client return a 201 status code 
	 * @return True if an account was created, False if a customer with that ID already exists 
	 * @throws SQLException **/
	public boolean createCustomer(String ssn) throws SQLException {
		if(!CheckWrongData.doesSomethingExistInTable(conn, CUSTOMERS, "ssn", ssn)) {
			SQLInsert.newCustomer(conn, ssn, "N/A");
			return true;
		}
		return false;
	}

	public boolean createAccountForCustomer(String ssn) throws SQLException {
		if(CheckWrongData.doesSomethingExistInTable(conn, CUSTOMERS, "ssn", ssn)) {
			int accountNumber = SQLInsert.newAccount(conn);
			SQLInsert.assign(conn, ssn, accountNumber);
			return true;
		}
		return false;
	}
	
	

}
