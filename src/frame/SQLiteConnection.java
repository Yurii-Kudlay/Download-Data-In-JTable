package com.sql.jdbc.frame;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SQLiteConnection {
	
	private static Connection conn;
	
	private static String nameJDBC;
	private static String dbPath;
	
	public SQLiteConnection(String nameJDBC, String dbPath) {
		super();
		this.nameJDBC = nameJDBC;
		this.dbPath = dbPath;
	}

	public static Connection getConnection (){
		try{
			Driver driver = (Driver) Class.forName(nameJDBC).newInstance();
			
			String url = dbPath ;
			
			if (conn == null){
			conn = DriverManager.getConnection(url);
			return conn;
			}
			
		}catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex){
			Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
