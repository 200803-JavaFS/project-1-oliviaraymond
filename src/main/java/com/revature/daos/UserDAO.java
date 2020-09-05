package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.User.UserType;
import com.revature.utils.HibernateUtil;

public class UserDAO implements IUserDAO {
	
	public UserDAO() {
		super();
	}

	@Override
	public List<User> findAll() {
		Session ses = HibernateUtil.getSession();

		List<User> list = ses.createQuery("FROM User").list();

		return list;
	}

	@Override
	public User findByUsername(String username) {
		Session ses = HibernateUtil.getSession();

		return ses.get(User.class, username);
	}

	@Override
	public boolean addUser(User u) {
		Session ses = HibernateUtil.getSession();

		
		Transaction tx = ses.beginTransaction();
		
		ses.save(u);
		
		tx.commit();
		return true;

//		try {
//			ses.save(u);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		
	}

	@Override
	public boolean updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();

		try {
			ses.merge(u);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.commit();
			return false;
		}
		
	}

	@Override
	public User findByUserID(int id) {
		Session ses = HibernateUtil.getSession();

		return ses.get(User.class, id);
	}

	@Override
	public User findByCredentials(String username, String password) {
		Session ses=HibernateUtil.getSession();
		List<User> userList = ses.createQuery("FROM User WHERE username ='"+username+ "' AND password='"+password+"'", User.class).list();
		User u= userList.get(0);
		return u;
	}

	@Override
	public User findByUserRole(UserType userType) {
		Session ses=HibernateUtil.getSession();
		List<User> userList = ses.createQuery("FROM User WHERE userType="+ userType, User.class).list();
		return userList.get(0);
	}

//	@Override
//	public Reimbursement findUserReimbursements(User u) {
//		Session ses=HibernateUtil.getSession();
//		List<Reimbursement> reimbList = ses.createQuery("FROM Reimbursement WHERE author ='"+u.getId()+"'", Reimbursement.class).list();
//		return reimbList;
//	}

	@Override
	public boolean deleteUser(int id) {
		Session ses = HibernateUtil.getSession();

		try {
			ses.createQuery("DELETE FROM User WHERE userId =" + id);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}


}
