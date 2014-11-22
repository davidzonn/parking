package businessLogic;

import beans.User;
import dataAccess.UserDataAccess;;
public class UserManager {
	private User user;
	/**
	 * 
	 * @param username
	 * @param password
	 * @return true if username is registered. False otherwise.
	 */
	public UserManager() {
	}
	public boolean isValidUser() {
		return true;
		//dao.validateUser(user);
		//return user.isValid();
	}
	public boolean isAdmin() {
		return true;
	}
}
