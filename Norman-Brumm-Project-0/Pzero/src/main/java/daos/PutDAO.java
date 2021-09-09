package daos;

import java.sql.Connection;
import java.sql.SQLException;

import sqlCommands.SQLPatch;
import tools.CheckWrongData;

public class PutDAO {
	Connection conn;
	private final static String CUSTOMERS = "customers";
	private final static String ACCOUNTS = "accounts";
	private final static String USERACCOUNTS = "useraccounts";
	
	public PutDAO(Connection conn) {
		this.conn = conn;
	}
	/** @return True if name was updated. False if the customer does not exist  **/
	public boolean updateCustomerName(String ssn, String name) throws SQLException {
		if(CheckWrongData.doesSomethingExistInTable(conn, CUSTOMERS, "ssn", ssn)) {
			SQLPatch.updateValue(conn, CUSTOMERS, "name", name, "ssn", ssn);
			return true;
		}
		return false;
	}
	public boolean updateAccountAmount(String ssn, String accountNumber, Double amt) throws SQLException {
		if(CheckWrongData.doesSomethingExistInTable(conn, USERACCOUNTS, "ssn=" + ssn + " AND account", accountNumber)) {
			SQLPatch.updateValue(conn, ACCOUNTS, "amount", String.valueOf(amt), "account", accountNumber);
			return true;
		}
			
		return false;
	}
}
