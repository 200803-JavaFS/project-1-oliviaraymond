package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.UserService;

public class LoginController {
	
	private static UserService us= new UserService();
	private static ObjectMapper om= new ObjectMapper();
	private static LoginService ls= new LoginService();
	
	RequestDispatcher rd= null;

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		

		if(req.getMethod().equals("GET")) {
			if (req.getParameterMap().containsKey("username")&& req.getParameterMap().containsKey("password")) {			
				User u= new User();
				//returns string: actual value input
				u.setUsername(req.getParameter("username"));
				u.setPassword(req.getParameter("password"));
				
				//now we need to check if it matches user we want to create (business logic)
				//thus, need a service login
				
				if(ls.login(u)) {
					HttpSession ses = req.getSession();
					
					ses.setAttribute("user", u);
					ses.setAttribute("loggedin", true);
					res.setStatus(200);
					res.getWriter().println("Login Successful");
				} else {
					HttpSession ses = req.getSession(false);
					
					if (ses!=null) {
						
						ses.invalidate();
					}
					res.setStatus(401);
					res.getWriter().println("Login failed");
				}
			}
		} else if (req.getMethod().equals("POST")) {
			BufferedReader reader = req.getReader();
			
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
				System.out.println("sb appended:" +sb);
			}
			
			String body = new String(sb);
			System.out.println(body);
			User u= om.readValue(body, User.class);
			
			
			if(ls.login(u)) {
				//System.out.println("hash: "+ u.getPassword().hashCode());
				
				
				HttpSession ses = req.getSession();
				
				
				ses.setAttribute("user", u);
								
				ses.setAttribute("loggedin", true);
				
				res.setStatus(200);
				res.getWriter().println("Login Successful");
				System.out.println("successful");
				
			} else {
				HttpSession ses = req.getSession(false);
				if (ses!=null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Login failed");
			}
		}
	}
	//another method for logout
	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession ses = req.getSession(false);
		
		if(ses!=null) {
			User u= (User) ses.getAttribute("user");
			ses.invalidate();
			res.setStatus(200);
			System.out.println("logout successful");
			res.getWriter().println(u.getUsername() + " has logged out successfully");
		} else {
			res.setStatus(400);
			res.getWriter().println("you must be logged in to logout!");

		}
		
	}
	
	
}
