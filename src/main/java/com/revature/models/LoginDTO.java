package com.revature.models;

public class LoginDTO {

	
	public String username;
	public String password; 
	public int userId;


public LoginDTO(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}

}