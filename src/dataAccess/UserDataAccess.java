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
			while (rs.next()) {
				System.out.println(rs.getString("username"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			rsSize = rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
