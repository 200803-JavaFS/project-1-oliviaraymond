package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;

public interface IReimbursementDAO {

	public List<Reimbursement> findAll();
	
	public Reimbursement findByReimID(int id);
		
	public boolean addReimbursement(Reimbursement r);
	// should these two be booleans?
	public boolean updateReimbursement(Reimbursement r);

	public List<Reimbursement> findReimbursementByStatus(ReimbursementStatus status);
	
	public List<Reimbursement> findReimbursementsByUser(User u);

	public List<Reimbursement> findReimbursementsByType(ReimbursementType type);
	
	public boolean deleteReimbursement(int id);


}
