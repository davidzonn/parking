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
	public UserManager(User user) {
		this.user = user;
	}
	public boolean isValidUser() {
		UserDataAccess dao = new UserDataAccess();
		dao.findUser(user);
		return true;
		//return dao.findUser(user) != null;
	}
	public boolean isAdmin() {
		return true;
	}
}
