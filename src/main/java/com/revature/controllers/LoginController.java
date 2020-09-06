package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.models.User.UserType;
import com.revature.services.LoginService;
import com.revature.services.UserService;

public class LoginController {

	private static LoginService ls = new LoginService();
	private static ObjectMapper om = new ObjectMapper();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {

		if (req.getMethod().equals("GET")) {
			// This shows logging in with query params for example purposes only. Do not ever
			// actually do this!
			if (req.getParameterMap().containsKey("username") && req.getParameterMap().containsKey("password")) {
				LoginDTO login = new LoginDTO();
				login.username = req.getParameter("username");
				login.password = req.getParameter("password");

				if (ls.login(login)) {
					HttpSession ses = req.getSession();
					ses.setAttribute("user", login);
					ses.setAttribute("loggedin", true);
					res.setStatus(200);
					res.getWriter().println("Login Successful");
				} else {
					HttpSession ses = req.getSession(false);
					if (ses != null) {
						ses.invalidate();
					}
					res.setStatus(401);
					res.getWriter().println("Login failed");
				}
			}
		} else if (req.getMethod().equals("POST")) {
			// this is how a login should generally be handled. Sending credentials in the
			// body of a POST request.
			BufferedReader reader = req.getReader();

			StringBuilder sb = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = new String(sb);

			LoginDTO login = om.readValue(body, LoginDTO.class);

			if (ls.login(login)) {
				HttpSession ses = req.getSession();
				ses.setAttribute("user", login);
				ses.setAttribute("loggedin", true);
				res.setStatus(200);
				res.getWriter().println("Login Successful");
			} else {
				HttpSession ses = req.getSession(false);
				if (ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Login failed");
			}

		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession ses = req.getSession(false);

		if (ses != null) {
			LoginDTO l = (LoginDTO) ses.getAttribute("user");
			ses.invalidate();
			res.setStatus(200);
			res.getWriter().println(l.username + " has logged out successfully");
		} else {
			res.setStatus(400);
			res.getWriter().println("You must be logged in to logout!");
		}
	}
}
//		if(reqBody.contains("username")) {
//
//			LoginDTO login = om.readValue(LoginDTO.class);
//	
//			if (ls.getUser(login.username) != null)
//			{
//				System.out.println("user found");
//				if (ls.login(login)) {// success
//					HttpSession ses = req.getSession();
//					User currentUser = ls.getUser(login.username);
////					int userID = currentUser.getUserId();
////					UserType userType = currentUser.getUserType();
//					//use these to control what the user can do 
//					ses.setAttribute("loggedin", true);
//					res.setStatus(200);
//					String json = om.writeValueAsString(login);
//					System.out.println(json);
//					res.getWriter().println(json);
//				} else {
//					// incorrect password
//					System.out.println("incorrect password entered");
//					HttpSession ses = req.getSession(false);
//					if (ses != null)
//						ses.invalidate();
//					res.setStatus(403);
//				}
//					
//			}else
//			{
//				res.setStatus(404);
//				res.getWriter().println("User " + login.username + " not found");
//			}
//			
//		}else
//		{
//			res.setStatus(400);
//			res.getWriter().println("Error: cannot login without supplying credentials");
//		}
//
//	}
//
//	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
//
//		HttpSession ses = req.getSession(false);
//		if (ses != null) {
//			LoginDTO l = (LoginDTO) ses.getAttribute("user");
//			ses.invalidate();
//			res.setStatus(200);
//			res.getWriter().println(l.username + " has logged out successfully");
//		} else {// 400 bad request
//			res.setStatus(400);
//			res.getWriter().println("You must be logged in to log out");
//		}
//
//	}
