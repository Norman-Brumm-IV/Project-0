package tools;

import java.sql.Connection;
import java.sql.SQLException;

import sqlCommands.SQLExists;

/** 
 * Checks if the input of things are correct for their fields. 
 *  **/
public class CheckWrongData {
	/** 
	 * Checks if any number of strings are empty
	 * @param strings - any number of strings
	 *  @return TRUE if any of the strings are empty.
	 *  **/
	public static boolean isEmptyString(String... strings) {
		for(String tempo : strings)
			if(tempo == null || tempo.isEmpty())
				return true;
		return false;
	}

	/**
	 * Checks that the ssn and account numbers are what they should be. Integers, 9 long. 
	 * @param strings - any number of strings. Expected length is 9
	 * @return TRUE if any of the strings are longer or shorter than 9, or are not Integers
	 *  **/
	public static boolean checkSSNAndAccountNumber(String... strings) {
		if(isEmptyString(strings))
			return true;
		for(String tempo : strings) {
			if(tempo.length()!=9)
				return true;
			if(!canParseAsInt(tempo))
				return true;
		}
		return false;
	}
	
	/** @return TRUE if all of the strings can be parsed as an Integer **/
	public static boolean canParseAsInt(String... isInt) {
		for(String tempo : isInt)
			try {
				if(tempo == null)
					return false;
				Double.parseDouble(tempo);
			} catch(NumberFormatException e) {
				// TODO replace with logging
				e.printStackTrace();
				return false;
			}
		return true;
	}
	
	/**
	 * Checks that some data exists in a database
	 * @param conn - Connection to the database
	 * @param tableName - table being checked for values. DOES NOT GET CHECKED FOR SQL INJECTION. Use care when using.
	 * @param column - column being checked for values. DOES NOT GET CHECKED FOR SQL INJECTION. Use care when using.
	 * @param columnValues - values being checked for
	 * @return True if all values were found. FALSE if any values were not found 
	 * @throws SQLException 
	 *  **/
	public static boolean doesSomethingExistInTable(Connection conn, String tableName, String column, String... columnValues) throws SQLException {
		return SQLExists.doesExist(conn, tableName, column, columnValues);
	}

}
