package dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import beans.User;

public class UserDataAccess {

	public Collection<String> resultSetToUser (ResultSet rs) throws SQLException {
		Collection<String> user = new java.util.ArrayList<String>();
		while (rs.next()) {
			
		}
		return user;
	}

	public boolean userExists(String username, String password) {
		String sql = "SELECT count(*) FROM USER"
				+ " WHERE username = '" + username
				+ "' AND password = '" + password + "'";
		DBConnect db = new DBConnect();
		int count = -1;
		ResultSet resultSet = db.executeStatement(sql);
		try {
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return count == 1;
	}
	
}
