package https;

import java.sql.SQLException;

import core.ConnectionDriver;
import daos.PostDAO;
import io.javalin.http.Context;
import tools.CheckWrongData;

public class Post {
	/** Creates a new client return a 201 status code 
	 * @throws SQLException **/
	public static void createCustomer(Context ctx) throws SQLException {
		PostDAO dao = new PostDAO(ConnectionDriver.getConnection());
		String ssn = ctx.pathParam("id");
		if(CheckWrongData.checkSSNAndAccountNumber(ssn)) {
			ctx.status(400);
			ctx.result("User ID missing or incorrect format");
			return;
		}
		if(dao.createCustomer(ssn)) {
			ctx.status(201);
			return;
		}
		ctx.status(409);
	}
	
	public static void createAccountForCustomer(Context ctx) throws SQLException {
		PostDAO dao = new PostDAO(ConnectionDriver.getConnection());
		String ssn = ctx.pathParam("id");
		if(CheckWrongData.checkSSNAndAccountNumber(ssn)) {
			ctx.status(400);
			ctx.result("User ID missing or incorrect format");
			return;
		}
		if(dao.createAccountForCustomer(ssn)) {
			ctx.status(201);
			return;
		}
		ctx.status(409);
		
	}
}
