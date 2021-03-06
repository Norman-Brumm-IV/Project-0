package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sqlCommands.SQLDelete;
import sqlCommands.SQLGet;
import tools.CheckWrongData;

public class DeleteDAO {
		Connection conn;
		private final static String CUSTOMERS = "customers";
		private final static String ACCOUNTS = "accounts";
		private final static String USERACCOUNTS = "useraccounts";
		
		public DeleteDAO(Connection conn) {
			this.conn = conn;
		}
		
		public void deleteAccount(int accountID) throws SQLException {
			SQLDelete.deleteRow(conn, ACCOUNTS, "account", String.valueOf(accountID));
		}
		
		/** @return True if the customer was deleted. False if nothing was changed**/
		public boolean deleteCustomer(int ssn) throws SQLException {
			if(!CheckWrongData.doesSomethingExistInTable(conn, CUSTOMERS, "ssn", String.valueOf(ssn)))
				return false;
			
			ResultSet rs = SQLGet.getInfoWhere(conn, USERACCOUNTS, "ssn", String.valueOf(ssn));
			while(rs.next())
				deleteAccount(rs.getInt("account"));
			
			SQLDelete.deleteRow(conn, CUSTOMERS, "ssn", String.valueOf(ssn));
			return true;
		}

		/** @return True if the customer was deleted. False if nothing was changed
		 * @throws SQLException **/
		public boolean deleteAccountSafely(int ssn, int accountNum) throws SQLException {
			if(!CheckWrongData.doesSomethingExistInTable(conn, CUSTOMERS, "ssn", String.valueOf(ssn)))
				return false;
			if(!CheckWrongData.doesSomethingExistInTable(conn, ACCOUNTS, "account", String.valueOf(accountNum)))
				return false;
			
			deleteAccount(accountNum);
			return true;
		}
}
