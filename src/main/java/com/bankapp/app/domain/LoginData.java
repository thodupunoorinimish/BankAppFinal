package com.bankapp.app.domain;

public class LoginData {

	 	private String username;
	    private String password;
	    
	    public LoginData() {}
	    
	    public LoginData(String username, String pass) {
	        super();
	        this.username = username;
	        this.password = pass;
	    }
	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String userName) {
	        this.username = userName;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }

	@Override
	public String toString() {
		return "LoginData{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
