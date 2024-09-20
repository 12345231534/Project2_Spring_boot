package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
	static final String  DB_URL = "jdbc:mysql://localhost:3306/javawebt9";
	static final String USER = "root";
	static final String PASS = "123456";
	
	public static Connection connect() {
		Connection conn = null;
		try {
			 conn =  DriverManager.getConnection(DB_URL,USER, PASS);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
