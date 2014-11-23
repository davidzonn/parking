package businessLogic;

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
		UserDataAccess dao = new UserDataAccess();
		return dao.userExists(username, password);
		
	}
	public boolean isAdmin(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
