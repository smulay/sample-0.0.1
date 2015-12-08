/**
 * 
 */
package com.persistent.sample_json.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author salil_mulay
 *
 */
public class DBConnection {

	private static Connection connection;
	private static Properties properties;
	
	public static Connection getConnection() {
		
		properties = new Properties();
		try {
			properties.load(new FileInputStream("connection.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (null == connection) {
			makeConnection();
		}		
		return connection;
	}
	
	private static void makeConnection() {
		System.out.println("Establishing database connection..");
		String url = properties.getProperty("db.url");
		String uname = properties.getProperty("db.username");
		String password = properties.getProperty("db.password");
		
		try {
			Class.forName(properties.getProperty("db.driver"));
			connection = DriverManager.getConnection(url, uname, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Database connection established.");
	}

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select count(*) cnt from preset_value");
		ResultSet resultSet = stmt.executeQuery();
		while(resultSet.next()) {
			System.out.println(resultSet.getLong("cnt"));
		}
	}

}
