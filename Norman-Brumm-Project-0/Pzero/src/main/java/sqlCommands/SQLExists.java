package sqlCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Checks if data can be found **/
public class SQLExists {

	/**
	 * Checks that some data exists in a database 
	 * @param conn - Connection to the database
	 * @param tableName - table being checked for values. DOES NOT GET CHECKED FOR SQL INJECTION. Use care when using.
	 * @param column - column being checked for values. DOES NOT GET CHECKED FOR SQL INJECTION. Use care when using.
	 * @param columnValues - values being checked for
	 * @return FALSE if any values were not found 
	 * @throws SQLException - If the table or column does not exist in the database where it should.
	 * **/
	public static boolean doesExist(Connection conn, String tableName, String column, String[] columnValues) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE " + column + "=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs;
		for(int i=0;i<columnValues.length;i++) {
			pstmt.setString(1, columnValues[i]);
			rs = pstmt.executeQuery();
			if(!rs.next())
				return false;
		}
		return true;
	}

}
