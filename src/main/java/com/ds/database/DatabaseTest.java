package com.ds.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.heroku.sdk.jdbc.DatabaseUrl;

public class DatabaseTest {

	
	  public static String dbTaste() throws Exception {
	      
		  	String result = "";
		  
	        Connection  connection = DatabaseUrl.extract().getConnection();
	        
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
	        while (rs.next()) {
	        	result+=("Read from DB: " + rs.getTimestamp("tick"));
	        }
	        if(connection != null)
	        	connection.close();
			return result;
	    }

	
}
