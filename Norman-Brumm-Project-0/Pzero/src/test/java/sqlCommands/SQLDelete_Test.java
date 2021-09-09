package sqlCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SQLDelete_Test {
	
	@Mock PreparedStatement mockPstmt;
	@Mock Connection mockConn;
	
	@Test
	public void testDeleteRow() throws SQLException {
		String columnEquals = "mockColumnEquals";
		String sql = "SQL stuff goes here";
		
		Mockito.when(mockConn.prepareStatement(sql)).thenReturn(mockPstmt);
		Mockito.doNothing().when(mockPstmt).setString(1, columnEquals);
		Mockito.doNothing().when(mockPstmt).execute();
	}

}
