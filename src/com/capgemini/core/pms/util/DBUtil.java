package com.capgemini.core.pms.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException
	 {
		 Properties dbProperties = loadProperties();
		 Class.forName(dbProperties.getProperty("drivername"));
			
			Connection con =
				DriverManager.getConnection(dbProperties.getProperty("url"),
						   dbProperties.getProperty("username"),
						  dbProperties.getProperty("password") );
			return con;
			
	 }
	 
	 private static Properties loadProperties() throws IOException
	 {
		
		 
		 InputStream inStream = new FileInputStream("oracle.properties");
		 
		 Properties dbProperties = new Properties();
		 
		 dbProperties.load(inStream);
		 
		return dbProperties; 
	 }

}
