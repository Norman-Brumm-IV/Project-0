package https;

import java.sql.SQLException;

import core.ConnectionDriver;
import daos.PatchDAO;
import io.javalin.http.Context;
import tools.CheckWrongData;

public class Patch {
	/**  Withdraw/deposit given amount (Body: {"deposit":500} or {"withdraw":250} return 404 if no account or client exists return 422 if insufficient funds 
	 * @throws SQLException **/
	public static void depositWithdraw(Context ctx) throws SQLException {
		PatchDAO dao = new PatchDAO(ConnectionDriver.getConnection());
		String ssn = ctx.pathParam("id");
		String accountNumber = ctx.pathParam("accountNumber");
		
		if(CheckWrongData.checkSSNAndAccountNumber(ssn, accountNumber)) {
			ctx.status(400);
			ctx.result("User ID missing or incorrect format");
			return;
		}
		dao.deposotWithdraw(ctx, ssn, accountNumber);
	}
	
	/** transfer funds from account 7 to account 8 (Body: {"amount":500}) return 404 if no client or either account exists return 422 if insufficient funds **/
	public static void transfer(Context ctx) throws SQLException {
		PatchDAO dao = new PatchDAO(ConnectionDriver.getConnection());
		String ssn = ctx.pathParam("id");
		String accountFrom = ctx.pathParam("accountFrom");
		String accountTo = ctx.pathParam("accountTo");
		if(CheckWrongData.checkSSNAndAccountNumber(ssn, accountFrom, accountTo)) {
			ctx.status(400);
			ctx.result("User ID or account number(s) missing or incorrect format");
			return;
		}
		dao.transfer(ctx, ssn, accountFrom, accountTo);
		
	}

}
