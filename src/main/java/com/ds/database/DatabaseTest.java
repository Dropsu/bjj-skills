package com.ds.database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class DatabaseTest {

	/*  private static Connection getConnection() throws URISyntaxException, SQLException {
	        URI dbUri = new URI(System.getenv("DATABASE_URL"));

	        String username = dbUri.getUserInfo().split(":")[0];
	        String password = dbUri.getUserInfo().split(":")[1];
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

	        return DriverManager.getConnection(dbUrl, username, password);
	    }*/
	
	  public static void dbTaste() throws Exception {
	        
	        Connection  connection = DatabaseUrl.extract().getConnection();
	        
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
	        while (rs.next()) {
	            System.out.println("Read from DB: " + rs.getTimestamp("tick"));
	        }
	    }
	
	public static String sayHolloFromDb () {
		return "hollo from db";
	}
	
}
