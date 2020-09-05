package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimbursementDAO implements IReimbursementDAO {

	@Override
	public List<Reimbursement> findAll() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement").list();
		return list;
	}

	@Override
	public boolean addReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		try {
			ses.save(r);
			tx.commit();
			return true;
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		
	}

	@Override
	public boolean updateReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		try {
			ses.merge(r);
			tx.commit();
			return true;
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		
	}

	@Override
	public List<Reimbursement> findReimbursementsByUser(User u) {
		Session ses=HibernateUtil.getSession();
		List<Reimbursement> reimbList = ses.createQuery("FROM Reimbursement WHERE reimbAuthor ="+u.getUserId()+"'", Reimbursement.class).list();
		return reimbList;
	}

	@Override
	public List<Reimbursement> findReimbursementsByType(ReimbursementType type) {
		Session ses=HibernateUtil.getSession();
		List<Reimbursement> reimb = ses.createQuery("FROM Reimbursement WHERE reimbStatus="+type, Reimbursement.class).list();
		return reimb;
	}

	@Override
	public Reimbursement findByReimID(int id) {
		Session ses=HibernateUtil.getSession();
		return ses.get(Reimbursement.class,id);
	}

	@Override
	public List<Reimbursement> findReimbursementByStatus(ReimbursementStatus status) {
		Session ses=HibernateUtil.getSession();
		List<Reimbursement> reimb = ses.createQuery("FROM Reimbursement WHERE reimbStatus="+status, Reimbursement.class).list();
		return reimb;
	}

	@Override
	public boolean deleteReimbursement(int id) {
		Session ses = HibernateUtil.getSession();

		try {
			ses.createQuery("DELETE FROM Reimbursement WHERE reimbID =" + id);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}


}
