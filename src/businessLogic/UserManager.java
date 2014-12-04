package businessLogic;

import java.sql.SQLException;

import beans.User;
import dataAccess.UserDataAccess;;
public class UserManager {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return true if username is registered. False otherwise.
	 */
	public UserManager() {
	}
	
	public boolean isValidUser(String username, String password) {
		boolean exists = false;	
		UserDataAccess dao = new UserDataAccess();
		try {
			exists = dao.userExists(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	} 
	public boolean isAdmin(String username) {
		boolean  isAdmin = false;
		UserDataAccess dao = new UserDataAccess();
		try {
			isAdmin = dao.isAdmin(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAdmin;
	} 
}
