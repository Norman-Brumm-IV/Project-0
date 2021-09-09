package https;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import daos.DeleteDAO;
import io.javalin.http.Context;
import tools.CheckWrongData;

@RunWith(MockitoJUnitRunner.class)
public class Delete_Test {

	@Mock DeleteDAO mockDao;
	@Mock Connection mockConn;
	@Mock ResultSet mockRS;
	@Mock Context mockCtx;
	@Mock CheckWrongData mockCwd;
	
	@Before 
	public void setUp() {
		
	}
	
	@Test
	public void testDelCustomerID() throws SQLException {
		// Arrange
		Mockito.when(mockCtx.pathParam("id")).thenReturn("123006789");
		Mockito.when(mockCwd.checkSSNAndAccountNumber("123006789")).thenReturn(false);
		Mockito.when(mockDao.deleteCustomer(123006789)).thenReturn(false);
		Mockito.doNothing().when(mockCtx.status(205));
	}
}
