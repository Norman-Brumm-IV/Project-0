package core;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DriverManager;

public class ConnectionDriver {

    private static Connection connection;

    private ConnectionDriver() {

    }

    public static Connection getConnection() {
        if(connection == null) {
            try {
            	
            	Properties props = new Properties();
            	FileReader fr = new FileReader("src/main/resources/connection.properties");
            	
            	props.load(fr);
            	
            	String connectionString = "jdbc:mariadb://" + 
            	props.getProperty("endpoint") + ":" + 
            	props.getProperty("port") + "/" +
            	props.getProperty("dbname") + "?user=" +
    			props.getProperty("userName") + "&password=" +
            	props.getProperty("password");
            	
                connection = DriverManager.getConnection(connectionString);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                //TODO: make the exception handling better
            }
        }
        return connection;
    }

}