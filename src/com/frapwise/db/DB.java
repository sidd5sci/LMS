package com.frapwise.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * base class for connection to the database for all Models
 * @author siddhartha
 *
 */
public class DB {

	/**
	 * private connection variable
	 */
	private Connection conn;
	
	/**
	 * default constructor for initiating the connecting to the database
	 * @return Object
	 */
	public DB(){
		this.conn = null;
		
		String driverName 	= "com.mysql.cj.jdbc.Driver";
		String url 			= "jdbc:mysql://localhost:3306/leave_mng";
		String user 		= "root";
		String pass 		= "";
		
		try {
			Class.forName(driverName);
						
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			this.conn = DriverManager.getConnection(url,user,pass);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Return the connection variable 
	 * @return Connection
	 */
	public Connection getConnection() {return this.conn;}
	
	/**
	 * Closes all database resources
	 * 
	 * @param connection
	 * @param stmt
	 * @param rs
	 */
	public void closeResources(Connection connection, Statement stmt,
			ResultSet rs)  {

		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			
		}

	}
	
}

/**
 resources bundles , singleton class
*/