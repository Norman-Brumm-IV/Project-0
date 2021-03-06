package sqlCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLDelete {
	/** DELETE FROM tableName WHERE columnName = columnEquals **/
	public static void deleteRow(Connection conn, String tableName, String columnName, String columnEquals) throws SQLException {
		//   sql = "DELETE FROM accounts WHERE account = ?";
		String sql = "DELETE FROM " + tableName + " WHERE " + columnName + "=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, columnEquals);
		pstmt.execute();
	}

}
