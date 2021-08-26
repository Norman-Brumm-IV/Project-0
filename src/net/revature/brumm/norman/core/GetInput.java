package net.revature.brumm.norman.core;

//importing and 'using' them all (just sending the args to the correct class)
// thinking about separating them all into a 'master' class for each of the Delete, Get, Patch, Post and Put classes. It will depend on how the input comes in
import net.revature.brumm.norman.delete.*; 
import net.revature.brumm.norman.get.*;
import net.revature.brumm.norman.patch.*;
import net.revature.brumm.norman.post.*;
import net.revature.brumm.norman.put.*;

public class GetInput {
	
	//this class will get the input from whatever we are using to get input and then send it out from here
	
	public static void main(String[] args) {
		// This args declaration is just so I can test it without additional steps
		args = new String[]{"POST", "CLIENTS", ""};
		// TODO remove line 8-10
		// TODO remove the placeHolder boolean so that each line that it needs replacing can be replaced. 
		boolean placeHolder = false;
		
		// just going down the list as its shown in my package explorer. I will be replacing the placeHolder variable with some kinda string parsing
		
		//TODO DeleteClientsVar if statement
		if(placeHolder)
			DeleteClientsVar.delCliV(args);
		
		//TODO DeleteClientsVarAccounts if statement
		if(placeHolder)
			DeleteClientsVarAccountsVar.delCliVarAcc(args);
		
		//TODO GetClients
		if(placeHolder)
			GetClients.getCli(args);
		
		//TODO GetClientsVar
		if(placeHolder)
			GetClientsVar.getCliVar(args);
		
		//TODO GetClientsVarAccounts
		if(placeHolder)
			GetClientsVarAccounts.getCliVarAcc(args);
		
		//TODO GetClientsVarAccountsBetween
		if(placeHolder)
			GetClientsVarAccountsBetween.getCliVarAccBet(args);
		
		//TODO GetClientsVarAccountsVar
		if(placeHolder)
			GetClientsVarAccountsVar.getCliAccVar(args);
		
		//TODO PatchClientsVarAccountsVar
		if(placeHolder)
			PatchClientsVarAccountsVar.patCliVarAccVar(args);
		
		//TODO PatchVlientsVarAccountsVarTransferVar
		if(placeHolder)
			PatchClientsVarAccountsVarTransferVar.patCliVarAccVarTra(args);
		
		//TODO PostClients
		if(placeHolder)
			PostClients.posCli(args);
		
		//TODO PostClientsVarAccounts
		if(placeHolder)
			PostClientsVarAccounts.posCliVarAcc(args);
		
		//TODO PutClientsVar
		if(placeHolder)
			PutClientsVar.putCliVar(args);
		
		//TODO PutClientsVarAccounts
		if(placeHolder)
			PutClientsVarAccountsVar.putCliVarAcc(args);
	}

}
