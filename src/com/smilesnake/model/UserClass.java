package com.smilesnake.model;

/**
 * @author Damitha_Nuwan
 *
 */
public class UserClass {
	private String username;
	private String email;
	private String password;	
	//private String confirmPassword;
	
	public UserClass(String username, String email, String password) {
		super();
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username.length()>=8 )//&& username.matches(UsernameValidator.regularExpression))
			this.username = username;
		else
			this.username = ("false");
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(email.length()>=8)
			this.email = email;
		else
			this.email = ("false");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password.length()>=8)
			this.password = password;
		else
			this.password = ("false");
	}
}
class UsernameValidator {
    public static final String regularExpression = "^[a-zA-Z][a-zA-Z0-9_]{6,19}$";
}
