package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBConnect {
	
	private Connection con;
	public DBConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ DBConfig.DBNAME, DBConfig.DBUSERNAME, DBConfig.DBPASSWORD);
		} catch (Exception e) {
			System.out.println("INTERNAL ERROR: " + e.getMessage());
		}
	}
	/**
	 * Call when finished with your sql management to free resources.
	 */
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return a connection. If only using simple sql you might consider using executeStatement instead.
	 */
	public Connection getConnection() {
		return con;
	}
	/**
	 * Executes an simple sqlStatement. For advanced options or if the Statement is needed you need to get a connection and
	 * execute these steps yourself.
	 * @return the result of executing the query.
	 */
	public ResultSet executeStatement(String sql) {
		Statement statement;
		ResultSet rs = null;
		try {
			statement = con.createStatement();
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
}
