package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import businessLogic.UserManager;

public class UserManagerTest{

	@Test
	public void testIsValidUser() {
		UserManager um = new UserManager();
		boolean valid;
		valid = um.isValidUser("pedro", "pedro");
		assertTrue(valid);
		
		valid = um.isValidUser("rato", "admin");
		assertTrue(valid);
		
		valid = um.isValidUser("batata123", "whatever!");
		assertFalse(valid);
	}

	@Test
	public void testIsAdmin() {
		
	}

}
