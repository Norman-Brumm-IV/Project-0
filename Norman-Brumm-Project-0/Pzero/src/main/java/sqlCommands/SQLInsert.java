package sqlCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLInsert {
	/**	Creates a new client. Returns a 201 status code if successful, and a 400 if not enough info or the wrong type. 409 if the user already exists**/
	public static void newCustomer(Connection conn, String ssn, String custName) throws SQLException {
		String sql = "INSERT INTO customers (ssn, name) VALUES (?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, ssn);
		pstmt.setString(2, custName);
		pstmt.execute();
	}
	/**	Creates a new client. Returns the account number if successful
	 * @param conn - Connection to the database 
	 * @throws SQLException if the table doesnt exist or was changed**/
	public static int newAccount(Connection conn) throws SQLException {
		String sql = "INSERT INTO accounts (amount) VALUES (-1)";
		PreparedStatement pstmt = conn.prepareStatement(sql); // TODO remember how to do the SQL WITHOUUT PreparedStatement
		pstmt.execute();
		
		ResultSet rs = SQLGet.getInfoWhere(conn, "accounts", "amount", "-1");
		rs.next();
		
		int accountNumber = rs.getInt("account");
		SQLPatch.updateValue(conn, "accounts", "amount", "0", "account", String.valueOf(accountNumber));
		
		return accountNumber;
	}
	/** 
	 * Assigns a user to an account
	 * @param conn - Connection to the database
	 * @param ssn - unique identifier from the customers table
	 * @param accountNumber - unique identifier from the accounts table
	 * @return 404 if the account or user does not exist. 400 if the ssn or accountNumber are empty or the wrong syntax. 201 if everything worked
	 * **/
	public static void assign(Connection conn, String ssn, int accountNumber) throws SQLException {
		String sql = "INSERT INTO useraccounts (ssn, account) VALUES (?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ssn);
		pstmt.setInt(2, accountNumber);
		pstmt.execute();
	}

}
