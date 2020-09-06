package com.revature.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbController;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.User;
import com.revature.models.User.UserType;
import com.revature.services.UserService;


public class MasterServlet extends HttpServlet{

	private static ReimbController rc = new ReimbController();
	private static	LoginController lc= new LoginController();
//	private static UserService us = new UserService();


	
	public MasterServlet() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("In servlet");
		res.setContentType("application/json");
		// By default tomcat will send back a successful status code if it finds a
		// servlet method.
		// Because all requests will hit this method, we are defaulting to not found and
		// will override for success requests.
		// res.setStatus(404);

		final String URI = req.getRequestURI().replace("/project0/", "");

		// example URI = avenger/1 to get avenger with ID 1

		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));
		if(portions.length==0) {
			req.getRequestDispatcher("index.html").forward(req, res);
		}
		try {
			switch (portions[0]) {
			case "avenger":
				if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
					if (req.getMethod().equals("GET")) {
						if (portions.length == 2) {
							int id = Integer.parseInt(portions[1]);
							rc.getReimbursement(res, id);
						} else if (portions.length == 1) {
							rc.getAllReimbursements(res);
						}
					} else if (req.getMethod().equals("POST")) {
						rc.addReimbursement(req, res);
					}
				} else {
					res.setStatus(403);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
			case "login":
				System.out.println("In login");
				lc.login(req, res);
				break;
			case "logout":
				lc.logout(req, res);
				break;
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.getWriter().print("The id you provided is not an integer");
			res.setStatus(400);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
//	
//		res.setContentType("application/json");
//		//tomcat by default will send back a success status code when it finds a servlet method
//		res.setStatus(404);
//		//all requests will hit this method... we default to this and then override for success requests
//		
//		final String URI= req.getRequestURI().replace("/project1/", "");
//		//example URI = user/1 to get user with id of 1 
//		String[] portions = URI.split("/");
//		
//
//		System.out.println(Arrays.toString(portions));
//		System.out.println("portions[0]:"+ portions[0]);
//		if(portions.length==0) {
//			req.getRequestDispatcher("index.html").forward(req, res);;
//		}
//		
//		try {
//			System.out.println("inside try");
//			//replace with a logger
//			switch(portions[0]) {
//				case "login":
//					lc.login(req,res);
//					break;
//				case "logout":
//					lc.logout(req,res);
//					break;
//				case "success": 
//					if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
//						User u = (User) req.getSession().getAttribute("user");
//						
//						System.out.println("**************** User username: "+  u.getUsername());
//						
//						u = us.findByUsername(u.getUsername());
////						UserType ur= u.getUserType();	
////						System.out.println(ur);
////						if(req.getMethod().equals("GET")) {
////							uc.setUserType(req, res, u);
////						}
//					}
//					break;
//				case "reimbursements":
//					System.out.println("in reimbursements");
//					if (portions.length==2){
//						int id= Integer.parseInt(portions[1]);
//						rc.getAllReimbursementsByAuthor(res, id);
//					}else {
//						rc.getAllReimbursements(res);
//					}
//					
//					break;
//				case "updateStatus":
//					System.out.println("in update status");
//					rc.updateStatus(req,res);
//					break;
//				case "addReimbursement":
//					System.out.println("in add reimbursement");
//					rc.addReimbursement(req,res);
//					break;
////				case "filter":
////					System.out.println("in filter");
////					if (portions.length==2){
////						ReimbursementStatus status= Integer.parseInt(portions[1]);
////						System.out.println(status);
////						rc.getAllReimbursementsByStatus(res, status);
////					}else {
////						rc.getAllReimbursements(res);
////					}
////					
////					break;
//			}
//					
//		}catch (NumberFormatException e ) {
//			e.printStackTrace();
//			res.getWriter().print("The id you provided is not an integer");
//			res.setStatus(400);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
//	}
//	
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
//		doGet(req,res);
//	}
	
}