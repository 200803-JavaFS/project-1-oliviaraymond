package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;
import com.revature.models.ReimbursementDTO;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbController {

	private static ReimbursementService rs = new ReimbursementService();
	private static ObjectMapper om= new ObjectMapper();
	private static UserService us= new UserService();

	
	public void getReimbursement(HttpServletResponse res, LoginDTO userDTO) throws IOException {
		User user = rs.getUser(userDTO);
		List<Reimbursement> reimb = rs.findByUser(user);
		if(reimb == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(reimb);
			res.getWriter().println(json);
		}
		
	}
	public void getReimbursement(HttpServletResponse res, int id) throws IOException {
		Reimbursement reimb = rs.findById(id);
		if(reimb == null) {
			res.setStatus(204);
		} else {
			res.setStatus(200);
			String json = om.writeValueAsString(reimb);
			res.getWriter().println(json);
		}
		
	}
	public void getAllReimbursements(HttpServletResponse res) throws IOException {
		List<Reimbursement> allReimb=  rs.findAll();
		
		res.getWriter().println(om.writeValueAsString(allReimb));
		res.setStatus(200);
	}
	
	public void getAllReimbursementsByAuthor(HttpServletResponse res, int id) throws IOException {
		User u=us.findById(id);
		List<Reimbursement> allReimb= rs.findByUser(u);

		if(allReimb.isEmpty()) {
			res.setStatus(204);
		}else {
			res.setStatus(200);
			String json = om.writeValueAsString(allReimb);
			res.getWriter().println(json);
		}
	
	}

	public void updateStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader= req.getReader();
		StringBuilder s=new StringBuilder();
		String line = reader.readLine();
		while(line!=null) {
			s.append(line);
			line = reader.readLine();
		}
		
//		String body = new String(s);
//		ReimbursementDTO rdto= om.readValue(body, ReimbursementDTO.class);
//		System.out.println("body that is taken in from java: "+ rdto);
//		
//		int reimbId= rdto.getId();
//		Reimbursement r = rs.findById(reimbId);
//		
//		ReimbursementStatus status= rdto.getStatus();
//		System.out.println("new status:" + status);
//		
//		ReimbursementStatus rStatus = status;
//		
//		int resolverId= rdto.getAuthorId();
//		System.out.println("resolver id: "+ resolverId);
//		
//		
//		r.setReimbStatus(rStatus);
//		r.setTimeResolved(new Timestamp(System.currentTimeMillis()));
//		User resolver= us.findById(resolverId);
//		System.out.println("resolver: " + resolver);
//		r.setReimbResolver(resolver);
//		System.out.println(r);
//		
//		if (rs.updateReimbursement(r)) {
//			res.setStatus(201);
//			res.getWriter().println("Reimbursement was updated");
//		}else {
//			res.setStatus(403);
//		}
//		
	}

	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		String line= reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();								
		}
		String body = new String(s);
	
		System.out.println(body);
		
		ReimbursementDTO rdto= om.readValue(body, ReimbursementDTO.class);
		System.out.println("body that is taken in from java: "+ rdto);
//		
//		double amount= rdto.getAmount();
//		Timestamp ts= new Timestamp(System.currentTimeMillis());
//		String description = rdto.getDescription();
//		User author = us.findById(rdto.getAuthorId());
//		
//		ReimbursementStatus rnewStatus= ReimbursementStatus.PENDING;
//		
//		ReimbursementType type = rdto.getType();
//		System.out.println(type);
//		
//		ReimbursementType rt= type;
//		if (type.equals(ReimbursementType.LODGING)) {
//			rt= rs.findByType(ReimbursementType.LODGING);
//		}else if (type.equals(ReimbursementType.TRAVEL)) {
//			rt= rs.findByType(ReimbursementType.TRAVEL);
//		}else if (type.equals(ReimbursementType.FOOD)) {
//			rt=rs.findByType(ReimbursementType.FOOD);
//		}else if (type.equals(ReimbursementType.OTHER)) {
//			rt=rs.findByType(ReimbursementType.OTHER);
//		}
//		
//		Reimbursement addedReimb=new Reimbursement(amount, ts,null,description, author, null, rnewStatus, rt);
//		System.out.println(addedReimb);

		if(rs.addReimbursement(rdto)) {
			//add to database
			res.setStatus(200);
			res.getWriter().println("Reimbursement was created");
		}else {
			res.setStatus(403);
		}
		
	}

	public void getAllReimbursementsByStatus(HttpServletResponse res, ReimbursementStatus status) throws IOException {
		List<Reimbursement> allReimb = rs.findByStatus(status);
		
		
		if(allReimb.isEmpty()) {
			res.setStatus(204);
		}else {
			res.setStatus(200);
			String json = om.writeValueAsString(allReimb);
			res.getWriter().println(json);
		}
	}
	public void getAllReimbursementsByType(HttpServletResponse res, ReimbursementType type) throws IOException {
		List<Reimbursement> allReimb = rs.findByType(type);
		
		if(allReimb.isEmpty()) {
			res.setStatus(204);
		}else {
			res.setStatus(200);
			String json = om.writeValueAsString(allReimb);
			res.getWriter().println(json);
		}
	}


}
