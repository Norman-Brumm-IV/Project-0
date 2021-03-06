package https;

import java.sql.SQLException;

import core.ConnectionDriver;
import daos.PutDAO;
import io.javalin.http.Context;
import tools.CheckWrongData;

public class Put {
	/**  updates client with id of 12 return 404 if no such client exist **/
	public static void updateCustomerName(Context ctx) throws SQLException {
		PutDAO dao = new PutDAO(ConnectionDriver.getConnection());
		String ssn = ctx.pathParam("id");
		if(CheckWrongData.checkSSNAndAccountNumber(ssn)) {
			ctx.status(400);
			ctx.result("User ID missing or incorrect format");
			return;
		}
		String name =ctx.pathParam("name");
		if(dao.updateCustomerName(ssn, name)) {
			ctx.status(200);
			return;
		}
		ctx.status(404);
	}
	/** update account with the id 3 for client 10 return 404 if no account or client exists 
	 * @throws SQLException **/
	public static void updateAccountAmount(Context ctx) throws SQLException {
		PutDAO dao = new PutDAO(ConnectionDriver.getConnection());
		String ssn = ctx.pathParam("id");
		String accountNumber = ctx.pathParam("accountNumber");
		if(CheckWrongData.checkSSNAndAccountNumber(ssn, accountNumber)) {
			ctx.status(400);
			ctx.result("User ID missing or incorrect format");
			return;
		}
		String amount = ctx.pathParam("amount");
		if(!CheckWrongData.canParseAsInt(amount)) {
			ctx.status(400);
			ctx.result("Amount was not a number");
			return;
		}
		
		Double amt = Double.parseDouble(amount);
		if(dao.updateAccountAmount(ssn, accountNumber, amt)) {
			ctx.status(200);
			return;
			
		}
		ctx.status(404);
	}
}
