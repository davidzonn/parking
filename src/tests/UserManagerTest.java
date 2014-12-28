package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import services.UserManager;

public class UserManagerTest{

	@Test
	public void testIsValidUser() {
		UserManager um = new UserManager();
		boolean isValid;
		
		isValid = um.isValidUser("pedro", "pedro");
		assertTrue(isValid);
		
		isValid = um.isValidUser("rato", "admin");
		assertTrue(isValid);
		
		isValid = um.isValidUser("batata123", "whatever!");
		assertFalse(isValid);
	}

	@Test
	public void testIsAdmin() {
		
	}

}
