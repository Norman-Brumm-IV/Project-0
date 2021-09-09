package https;

import java.sql.SQLException;

import core.ConnectionDriver;
import daos.DeleteDAO;
import io.javalin.http.Context;
import tools.CheckWrongData;

/**	Holds all of the delete http calls **/
public class Delete {
	/**	deletes client with the id of 15 return 404 if no such client exist return 205 if success 
	 * @throws SQLException 
	 * @throws  **/
	public static void delCustomerID(Context ctx) throws SQLException {
		DeleteDAO dao = new DeleteDAO(ConnectionDriver.getConnection());
		String ssnID = ctx.pathParam("id");
		if(CheckWrongData.checkSSNAndAccountNumber(ssnID)) {
			ctx.status(400);
			ctx.result("User ID missing or incorrect format");
			return;
		}
		
		if(!(dao.deleteCustomer(Integer.parseInt(ssnID)))) {
			ctx.status(404);
			ctx.result("No such client exists");
			return;
		}
		
		ctx.status(205);
	}
	
	public static void deleteAccount(Context ctx) throws SQLException {
		DeleteDAO dao = new DeleteDAO(ConnectionDriver.getConnection());
		String ssnID = ctx.pathParam("id");
		String accountNum = ctx.pathParam("accountNumber");
		if(CheckWrongData.checkSSNAndAccountNumber(ssnID, accountNum)) {
			ctx.status(400);
			ctx.result("User ID/Account Number missing or incorrect format");
			return;
		}
		
		if(!dao.deleteAccountSafely(Integer.parseInt(ssnID), Integer.parseInt(accountNum))) {
			ctx.status(404);
			ctx.result("No such client exists");
			return;
		}
		
		ctx.status(205);
		
	}
}
