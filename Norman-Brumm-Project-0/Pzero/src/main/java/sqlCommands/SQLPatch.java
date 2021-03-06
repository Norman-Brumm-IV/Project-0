package sqlCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLPatch {

	/** Updates a row in a table
	 * UPDATE tableName SET setColumnName=setChange WHERE whereColumn=whereSelection 
	 * @param conn - Connection to the database
	 * @param tableName - Name of the table holding the data that needs to be updated
	 * @param setColumnName - Name of the column whos data needs to be changed
	 * @param setChange - New data of setColumnName's cell
	 * @param whereColumn - Column to be used for matching
	 * @param whereSelection - data that already exists in the database. Used as a pointer for the correct row thats being changed 
	 * **/
	public static void updateValue(Connection conn, String tableName, String setColumnName, String setChange, String whereColumn, String whereSelection) throws SQLException {
		// "UPDATE tableName SET setColumnName=setChange WHERE whereColumn=whereSelection";
		String sql = "UPDATE " + tableName + " SET " + setColumnName + "=? WHERE " + whereColumn + "=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, setChange);
		pstmt.setString(2, whereSelection);
		pstmt.execute();
	}

}
