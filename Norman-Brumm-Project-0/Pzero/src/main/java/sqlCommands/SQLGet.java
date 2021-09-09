package sqlCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This will hold all my SELECT statements and return ResultSets of their query. NO ERROR HANDLING IN THIS CLASS **/
public class SQLGet {
	/** returns a ResultSet with all of the table info in it. 
	 * @param conn - Connection to the database
	 * @param tableName - Name of the table your getting everything from
	 * @throws SQLException **/
	public static ResultSet getTable(Connection conn, String tableName) throws SQLException {
		String sql = "SELECT * FROM " + tableName;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// TODO change the error handling
		if(tableName.isEmpty())
			return null;
		
		return pstmt.executeQuery(); 
	}

	/** returns a ResultSet with a row of info from the table in it 
	 * this is a = operation, not a like operation
	 * SELECT * FROM tableName WHERE columnName = columnEquals
	 * @param conn - Connection to the database
	 * @param tableName - Name of the table being checked
	 * @param columnName - Name of the column being checked
	 * @param columnEquals - What the columnName is being checked against
	 * @throws SQLException 
	 **/
	public static ResultSet getInfoWhere(Connection conn, String tableName, String columnName ,String columnEquals) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// TODO change the error handling
		if(tableName.isEmpty())
			return null;

		pstmt.setString(1, columnEquals);
		
		return pstmt.executeQuery();
	}

	/** 
	 * returns a ResultSet with a row of info from the table in it 
	 * SELECT * FROM tableName WHERE {statement} AND {any number of other statements}.
	 * mathComparitors should be equal in length to compareWith. andOr - true is AND, false is OR.
	 * @param andOr - true is AND, false is OR
	 * @param conn - Connection to the database
	 * @param tableName - table(s) being checked. Not checked for injection, NEVER PASS THIS A VARIABLE YOU DIDNT HARDCODE YOURSELF.
	 * @param comparators - Array of string like {amount<} or {amount=} or {name LIKE}
	 * @param compareWith - Array of strings that are getting checked by the comparators. if this is not equal to comparators then returns a null
	 * @throws SQLException 
	 **/
	public static ResultSet getInfoWhereAndOr(boolean andOr, Connection conn, String tableName, String[] comparators, String[] compareWith) throws SQLException {
		//            SELECT * FROM useraccounts INNER JOIN (accounts) USING (account) WHERE ssn='461044607' AND amount > 400 AND amount < 850
		// my tableName and mathComparitors have SQL text inside of it. It is expected and this part of the string is never accessible to users
		String orAnd = " AND ";
		
		if(!andOr) {
			orAnd = " OR ";
		}
		
		// TODO change the error handling
		if(tableName.isEmpty() || comparators.length<1 || compareWith.length != comparators.length)
			return null;

		StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
	
		sql.append(comparators[0] + "?");
		
		for(int i=1;i<comparators.length;i++)
			sql.append(orAnd + comparators[i] + "?");

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		for(int i=0;i<compareWith.length;i++) {
			pstmt.setString(i+1, compareWith[i]);
		}
		return pstmt.executeQuery();
	}

}
