package core;

import https.*;

//importing and 'using' them all (just sending the args to the correct class)
// thinking about separating them all into a 'master' class for each of the Delete, Get, Patch, Post and Put classes. It will depend on how the input comes in
import io.javalin.Javalin;

public class GetInput {
	
	// TODO: Create generic get statements so I can replace things in the classes with calls to another class
	// TODO: make this actually use a DAO and not whats currently in place
	// TODO: look up error codes and use the correct ones when its not specified
	
	// these todos are things I will need to add to make multiple people assigned to accounts
	// TODO: add a user to an account that already exists
	// TODO: when removing a user, only remove the account if its not assigned to anyone else
	// TODO: remove a person from an account IF there is another person also assigned to it
	
	//this class will get the input from whatever we are using to get input and then send it out from here
	
	/*
	 *   USERNAME FOR DATABASE: neain2008
	 */
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
			// GetClients - returns all the client names seperated by a new line char
		app.get("/clients", Get::getAllClients);
			// GetClientsVar - needs a ssn number
		app.get("/clients/:id", Get::getClientNameByID);
			// GetClientsVarAccounts - needs a ssn number.
		app.get("/clients/:id/accounts", Get::getClientAccountByID);
			// GetClientsVarAccountsVar - needs ssn, account number
		app.get("/clients/:id/accounts/:accountNumber", Get::getAccountBalance);
			// DeleteClientsVar statement. The text input needs to just be an ID. This will delete all accounts of a user and so there needs to be a check of some sort before getting to this step
		app.delete("/clients/:id", Delete::delCustomerID);
			// DeleteClientsVarAccounts statement. Needs the ssn, then the account number. Currently split by a <>, will eventually adjust to take JSON. This will delete the account and void any money
		app.delete("/clients/:id/accounts/:accountNumber", Delete::deleteAccount);
			// PatchClientsVarAccountsVar - needs ssn, account number, deposit/withdraw, amount
		app.patch("/clients/:id/accounts/:accountNumber", Patch::depositWithdraw);
			//PatchVlientsVarAccountsVarTransferVar - Account number from, account number to, amount
		app.patch("//clients/:id/accounts/:accountFrom/transfer/:accountTo", Patch::transfer);
			// TODO: error handling if the person already exists
			// PostClients - needs ssn, name
		app.post("/clients/:id", Post::createCustomer);
			// PostClientsVarAccounts - needs ssn
		app.post("/clients/:id/accounts", Post::createAccountForCustomer);
			// PutClientsVar - needs ssn, new name
		app.put("/clients/:id/:name", Put::updateCustomerName );
		// PutClientsVarAccounts
		app.put("/clients/:id/accounts/:accountNumber/:amount", Put::updateAccountAmount);
	}
}
