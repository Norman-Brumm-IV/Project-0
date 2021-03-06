package https;

import java.sql.SQLException;
import java.util.ArrayList;

import core.ConnectionDriver;
import daoModels.AccountsTableRow;
import daoModels.CustomersRow;
import daoModels.UserAccountRow;
import daos.GetDAO;
import io.javalin.http.Context;
import tools.CheckWrongData;

public class Get {
	
	/**  
	 * Gets all clients
	 * @throws SQLException 
	 **/
	public static void getAllClients(Context ctx) throws SQLException {
		GetDAO dao = new GetDAO(ConnectionDriver.getConnection());
		ctx.json(dao.getAllCustomers());
	}
	
	/**get client with id of 10 return 404 if no such client exist 
	 * @throws SQLException 
	 * **/
	public static void getClientAccountByID(Context ctx) throws SQLException {
		GetDAO dao = new GetDAO(ConnectionDriver.getConnection());
		String ssnID = ctx.pathParam("id");
		if(CheckWrongData.checkSSNAndAccountNumber(ssnID)) {
			ctx.status(400);
			ctx.result("User ID missing or incorrect format");
			return;
		}
		
		ArrayList<UserAccountRow> rows = dao.getCustomerAccountByID(Integer.parseInt(ssnID));
		if(rows == null) {
			ctx.status(404);
			ctx.result("Customer not found");
			return;
		}
			
		/* 
		 * ---------------------------------------------
		 * checking for query here
		 * ---------------------------------------------
		 */ 
		String amountlessThan = ctx.queryParam("amountLessThan");
		if(amountlessThan != null) {
			getClientAccountsBetween(ctx, rows);
			return;
		}

		
		ctx.json(rows);
	}
	
	/**	get all accounts for client 7 between one number and a larger number return 404 if no client exists 
	 * @param rows 
	 * @throws SQLException 
	 * **/
	public static void getClientAccountsBetween(Context ctx, ArrayList<UserAccountRow> rows) throws SQLException {
		GetDAO dao = new GetDAO(ConnectionDriver.getConnection());
		String amountLessThan = ctx.queryParam("amountLessThan");
		String amountGreaterThan = ctx.queryParam("amountGreaterThan");
		
		if(!CheckWrongData.canParseAsInt(amountLessThan, amountGreaterThan)) {
			ctx.status(400);
			ctx.result("One or more of the ammounts was not a number");
			return;
		}
		
		Double alt = Double.parseDouble(amountLessThan);
		Double agt = Double.parseDouble(amountGreaterThan);
		
		ArrayList<AccountsTableRow> atr = dao.getAccountsBetween(rows, alt, agt);
		
		
		ctx.json(atr);
	}
	
	public static void getClientNameByID(Context ctx) throws SQLException {
		GetDAO dao = new GetDAO(ConnectionDriver.getConnection());
		String ssnID = ctx.pathParam("id");
		if(CheckWrongData.checkSSNAndAccountNumber(ssnID)) {
			ctx.status(400);
			ctx.result("User ID missing or incorrect format");
			return;
		}
		
		CustomersRow row = dao.getClientNameByID(Integer.parseInt(ssnID));
		if(row == null) {
			ctx.status(404);
			ctx.result("Customer not found");
			return;
		}
		ctx.json(row);
	}
	
	public static void getAccountBalance(Context ctx) throws SQLException {
		GetDAO dao = new GetDAO(ConnectionDriver.getConnection());
		String ssnID = ctx.pathParam("id");
		String accountNumber = ctx.pathParam("accountNumber");
		if(CheckWrongData.checkSSNAndAccountNumber(ssnID, accountNumber)) {
			ctx.status(400);
			ctx.result("User ID/Account Number missing or incorrect format");
			return;
		}
		
		AccountsTableRow atr = dao.getAccountBalance(Integer.parseInt(ssnID), Integer.parseInt(accountNumber));
		if(atr==null) {
			ctx.status(404);
			ctx.result("User ID or Account Number does not exist");
			return;
		}
			
		ctx.json(atr);
		
		
	}
}
