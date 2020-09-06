package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;
import com.revature.models.User;

public class ReimbursementService {
	
	private static final Logger log = LogManager.getLogger(ReimbursementService.class);
	private static IReimbursementDAO rDao= new ReimbursementDAO();
	
	//use format specifiers for better logging messages
	
	public List<Reimbursement> findAll(){
		log.info("Retrieving all reimbursements");
		return rDao.findAll();
	}
	
	public Reimbursement findById(int id) {
		log.info("Finding Reimbursement with id "+ id);
		return rDao.findByReimID(id);
	}	
	
	public List<Reimbursement> findByUser(User u) {
		log.info("Retrieving all reimbursements tied to user with user_id="+ u.getUserId());
		return rDao.findReimbursementsByUser(u);
	}
	
	public List<Reimbursement> findByStatus(ReimbursementStatus status){
		log.info("Finding ReimbStatus with status: "+ status);
		return rDao.findReimbursementByStatus(status);
	}
	
	public boolean addReimbursement(Reimbursement r) {
		log.info("Adding reimbursements: "+ r);
		return rDao.addReimbursement(r);
	}
	
	public boolean updateReimbursement(Reimbursement r) {
		log.info("Updating reimbursement: "+ r);
		return rDao.updateReimbursement(r);
	}
	public List<Reimbursement> findByType(ReimbursementType type) {
		log.info("Finding Reimbs with types: "+ type);
		return rDao.findReimbursementsByType(type);
	}
	public boolean deleteReimbursement(int id) {
		log.info("Deleting reimbursements: "+ id);
		return rDao.deleteReimbursement(id);
	}
	
}
