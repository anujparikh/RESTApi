package eden.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private final static String DB_URL = "jdbc:mysql://localhost:3306/restaurant_db";
	private final static String DB_USER = "root";
	private final static String DB_PASSWORD = "123456";

	// static block will execute as soon as the class is loaded
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC driver is loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error in loading MySQL JDBC Driver");
		}
	}
	
	public static Connection connect() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connection to MySQL SUCCESSFUL");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection to MySQL FAILED");
		}
		
		return conn;
	}
	
	public static void main(String[] args) {

		DBUtils.connect();
	}

}
