package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnect {
	public static final String DBUSERNAME = "root";
	public static final String DBPASSWORD = "";
	private Connection con;
	
	
	public DBConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306");
			
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
	}
	public Connection getConnection() {
		return con;
	}
	public Statement getStatement() {
		return null;
	}
}
