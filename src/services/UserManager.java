package services;

import java.sql.SQLException;

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
		exists = dao.userExists(username, password);
		return exists;
	} 
	public boolean isAdmin(String username) {
		boolean  isAdmin = false;
		UserDataAccess dao = new UserDataAccess();
		isAdmin = dao.isAdmin(username);
		return isAdmin;
	} 
}
