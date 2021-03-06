package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.javalin.http.Context;
import sqlCommands.SQLGet;
import sqlCommands.SQLPatch;
import tools.CheckWrongData;

public class PatchDAO {
	Connection conn;
	private final static String ACCOUNTS = "accounts";
	private final static String USERACCOUNTS = "useraccounts";
	
	public PatchDAO(Connection conn) {
		this.conn = conn;
	}
	
	/**  Withdraw/deposit given amount (Body: {"deposit":500} or {"withdraw":250} return 404 if no account or client exists return 422 if insufficient funds **/
	public void deposotWithdraw(Context ctx, String ssn, String accountNumber) throws SQLException {
		if(!(CheckWrongData.doesSomethingExistInTable(conn, USERACCOUNTS, "ssn= "+ ssn + " AND account", accountNumber))) {
			ctx.status(404);
			ctx.result("No such client with that account exists");
			return;
		}
		
		String[] jsonObject = jsonParser(ctx.body());
		if(jsonObject[0].equals("deposit")) {
			deposit(accountNumber, jsonObject[1]);
			ctx.status(201);
		} else if(jsonObject[0].equals("withdraw")) {
			if(!withdraw(accountNumber, jsonObject[1])) {
				ctx.status(422);
			}
		}
	}
	
	/** transfer funds from account 7 to account 8 (Body: {"amount":500}) return 404 if no client or either account exists return 422 if insufficient funds **/
	public void transfer(Context ctx, String ssn, String accountNumberFrom, String accountNumberTo) throws SQLException {
		if(!(CheckWrongData.doesSomethingExistInTable(conn, USERACCOUNTS, "ssn= "+ ssn + " AND account", accountNumberFrom))) {
			ctx.status(404);
			ctx.result("No such client with that account exists");
			return;
		}
		
		if(!(CheckWrongData.doesSomethingExistInTable(conn, USERACCOUNTS, "ssn= "+ ssn + " AND account", accountNumberTo))) {
			ctx.status(404);
			ctx.result("No such client with that account exists");
			return;
		}

		String[] jsonObject = jsonParser(ctx.body());
		if(!withdraw(accountNumberFrom, jsonObject[1])) {
			ctx.status(422);
			return;
		}
		deposit(accountNumberTo, jsonObject[1]);

		
	}

	private void deposit(String accountNumber, String amount) throws SQLException {
		ResultSet rs= SQLGet.getInfoWhere(conn, ACCOUNTS, "account", accountNumber);
		rs.next();
		Double currentAmount = rs.getDouble("amount");
		currentAmount+=Double.parseDouble(amount);
		SQLPatch.updateValue(conn, ACCOUNTS, "amount", String.valueOf(currentAmount), "account", accountNumber);
	}
	
	/** @return True if the money can be withdrawn. False if it would drop below 0 
	 * @throws SQLException **/
	private boolean withdraw(String accountNumber, String amount) throws SQLException {
		ResultSet rs= SQLGet.getInfoWhere(conn, ACCOUNTS, "account", accountNumber);
		rs.next();
		Double currentAmount = rs.getDouble("amount");
		currentAmount-=Double.parseDouble(amount);
		if(currentAmount<0)
			return false;
		SQLPatch.updateValue(conn, ACCOUNTS, "amount", String.valueOf(currentAmount), "account", accountNumber);
		return true;
	}
	
	/** Yes, I know this method is a monster **/
	private String[] jsonParser(String json) {
		String[] finishedJSON = new String[2];
		String[] parser = json.split("\"");
		finishedJSON[0] = parser[1];
		parser = parser[2].split(":");
		parser = parser[1].split("}");
		finishedJSON[1] = parser[0];
		return finishedJSON;
	}
	

}
