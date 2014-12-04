package dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import beans.User;

public class UserDataAccess {
	DBConnect db;
	ResultSet result;
	public UserDataAccess() {
		db = new DBConnect();
	}
	public Collection<String> resultSetToUser (ResultSet rs) throws SQLException {
		Collection<String> user = new java.util.ArrayList<String>();
		while (rs.next()) {
			
		}
		return user;
	}

	public boolean userExists(String username, String password) throws SQLException {
		String sql = "SELECT count(*)"
				+ "FROM USER"
				+ " WHERE username = '" + username
				+ "' AND password = '" + password + "'";
		result = db.executeStatement(sql);
		result.next();
		int count = result.getInt(1);
		return count == 1;
	}
	public boolean isAdmin(String username) throws SQLException {
		String sql = "SELECT count(*) "
				+ "FROM USER u "
				+ "JOIN ADMIN a ON u.ID_USER = a.ID_USER"
				+ " WHERE username = '" + username + "'";
		result = db.executeStatement(sql);
		result.next();
		int count = result.getInt(1);
		return count == 1;
	}
	
}
