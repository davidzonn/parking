package beans;

public class User {
	private String username;
	private String password;
	private boolean valid;
	public User() {
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		setValid(false);
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
