package dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import model.JPADataAccess;

public class UserDataAccess {
	ResultSet result;
	public UserDataAccess() {
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
		JPADataAccess jpa = new JPADataAccess();
		Query query = jpa.getQuery(sql);
		long result = (long)query.getSingleResult();
		return result == 1;
	}
	public boolean isAdmin(String username) throws SQLException {
		String sql = "SELECT count(*) "
				+ "FROM USER u "
				+ " WHERE username = '" + username + "'"
				+ " AND ACCESS_LEVEL > 1";
		JPADataAccess jpa = new JPADataAccess();
		Query query = jpa.getQuery(sql);
		long result = (long)query.getSingleResult();
		return result == 1;	}
}
