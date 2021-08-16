package com.techment.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDetails {

public static Connection getConnected() throws Exception{
		
		Class.forName(ConnectionProperty.DRIVER_NAME);
		Connection con=DriverManager.getConnection(ConnectionProperty.URL,ConnectionProperty.ID,ConnectionProperty.PASSWORD);
		System.out.println("Successfully...!");
		return con;

	}
	
}
