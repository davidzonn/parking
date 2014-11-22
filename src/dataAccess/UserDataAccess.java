package dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import beans.User;

public class UserDataAccess {

	public Collection findUser(User user) {
		DBConnect database = new DBConnect();
		String sql = "SELECT * FROM USER WHERE user.username = '" + user.getUsername() + "' AND user.password = '" + user.getPassword() + "'";
		ResultSet rs = database.executeStatement(sql);
		int rsSize;
		try {
			rs.last();
			rsSize = rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<String> resultSetToUser (ResultSet rs) throws SQLException {
		Collection<String> user = new java.util.ArrayList<String>();
		while (rs.next()) {
			
		}
		return user;
	}
	
}
