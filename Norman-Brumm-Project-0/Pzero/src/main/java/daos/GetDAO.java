package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daoModels.AccountsTableRow;
import daoModels.CustomersRow;
import daoModels.UserAccountRow;
import sqlCommands.SQLGet;
import tools.CheckWrongData;

public class GetDAO {
	Connection conn;
	private final static String customers = "customers";
	private final static String accounts = "accounts";
	private final static String userAccounts = "useraccounts";
	
	public GetDAO(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<CustomersRow> getAllCustomers() throws SQLException {
		ResultSet rs = SQLGet.getTable(conn, customers);
		ArrayList<CustomersRow> alcr = new ArrayList<CustomersRow>();
		while(rs.next())
			alcr.add(getAllCustomerColumnsFromResultSet(rs));
		return alcr;
	}
	
	public ArrayList<UserAccountRow> getCustomerAccountByID(int ssnID) throws SQLException {
		String columnName = "ssn";
		ResultSet rs = SQLGet.getInfoWhere(conn, userAccounts, columnName, String.valueOf(ssnID));
		ArrayList<UserAccountRow> alcr = new ArrayList<UserAccountRow>();
		while(rs.next()) {
			alcr.add(getAllUserAccountsColumnsFromResultSet(rs));
		}
		return alcr;
	}
	
	public ArrayList<AccountsTableRow> getAccountsBetween(ArrayList<UserAccountRow> uar, Double lessThan, Double greaterThan) throws SQLException {
		ArrayList<AccountsTableRow> tbr = new ArrayList<AccountsTableRow>(), 
									atr = new ArrayList<AccountsTableRow>();
		String[] mathThings = new String[uar.size()],
			 accountNumbers = new String[uar.size()];
		double currentAmount = 0;
		
		for(int i=0;i<uar.size();i++) {
			mathThings[i] = "account=";
			accountNumbers[i] = String.valueOf((uar.get(i)).getAccount());
		}
		
		ResultSet rs = SQLGet.getInfoWhereAndOr(false, conn, accounts, mathThings, accountNumbers);
		
		while(rs.next()) {
			atr.add(getAllAccountsColumnsFromResultSet(rs));
		}
		
		
		for(AccountsTableRow atrC : atr) {
			currentAmount = atrC.getAmountInAccount();
			if(currentAmount<lessThan && currentAmount>greaterThan)
				tbr.add(atrC);
		}
		
		return tbr;
	}
	/** 
	 * 
	 * @return null if the customer was not found
	 * **/	public CustomersRow getClientNameByID(int ssnID) throws SQLException {
		String columnName = "ssn";
		ResultSet rs = SQLGet.getInfoWhere(conn, customers, columnName, String.valueOf(ssnID));
		if(rs.next()) {
			String name = rs.getString("name");
			String id = rs.getString("ssn");
			CustomersRow cr = new CustomersRow();
			cr.setName(name);
			cr.setSsn(Integer.parseInt(id));
			return cr;
		}
		return null;
	}
	
		/** This expects the ResultSet to have all of the customers table columns
		 * @throws SQLException **/
		private static CustomersRow getAllCustomerColumnsFromResultSet(ResultSet rs) throws SQLException {
			int ssn = rs.getInt("ssn");
			String name = rs.getString("name");
			CustomersRow cr = new CustomersRow(ssn, name);
			return cr;
		}

		/** This expects the ResultSet to have all of the useraccouunts table columns
		 * @throws SQLException **/
		private static UserAccountRow getAllUserAccountsColumnsFromResultSet(ResultSet rs) throws SQLException {
			int ssn = rs.getInt("ssn");
			int accountNumber = rs.getInt("account");
			UserAccountRow cr = new UserAccountRow(ssn, accountNumber);
			return cr;
		}
		
		/** This expects the ResultSet to have all of the accouunts table columns
		 * @throws SQLException **/
		private static AccountsTableRow getAllAccountsColumnsFromResultSet(ResultSet rs) throws SQLException {
			double amount = rs.getDouble("amount");
			int accountNumber = rs.getInt("account");
			AccountsTableRow cr = new AccountsTableRow(accountNumber, amount);
			return cr;
		}

		public AccountsTableRow getAccountBalance(int ssn, int account) throws SQLException {
			AccountsTableRow atr = new AccountsTableRow();
			if(!CheckWrongData.doesSomethingExistInTable(conn, userAccounts, "ssn=" + ssn + " AND account", String.valueOf(account)))
				return null;
			
			ResultSet rs = SQLGet.getInfoWhere(conn, accounts, "account", String.valueOf(account));
			if(rs.next())
				atr.setBoth(rs.getInt("account"), rs.getDouble("amount"));
			return atr;
		}
}
