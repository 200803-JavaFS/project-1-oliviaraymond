package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;
import com.revature.models.User;

public class LoginService {
	
	public boolean login(LoginDTO login) {
		if(login.username.equals("liv") && login.password.equals("ray")) {
			return true;
		}
		return false; 
	}

//private static UserDAO udao = new UserDAO();
//	
//	public boolean login(LoginDTO login) {
//		
//		//check if user exists and password matches
//		User u = udao.findByUsername(login.username);
//		return(u!=null && login.password.equals((u.getPassword())));
//		}
//
//	public User getUser(String username) {
//		return udao.findByUsername(username);
//	}
}
