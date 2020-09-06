package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;
import com.revature.models.User;

public class LoginService {
	private static UserDAO udao = new UserDAO();
	public boolean login(LoginDTO login) {

		User u = udao.findByUsername(login.username);
		return(u!=null && login.password.equals((u.getPassword())));
		}
	}


//	public User getUser(String username) {
//		return udao.findByUsername(username);
//	}

