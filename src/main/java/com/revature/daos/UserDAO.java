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

		List<User> userList = ses.createQuery("FROM User WHERE username ='" + username + "'", User.class).list();
		User u = userList.get(0);
		return u;
	}

	@Override
	public boolean addUser(User u) {
		Session ses = HibernateUtil.getSession();

		try {
			ses.save(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		try {
			ses.merge(u);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
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
		Session ses = HibernateUtil.getSession();
		List<User> userList = ses
				.createQuery("FROM User WHERE username ='" + username + "' AND password='" + password + "'", User.class)
				.list();
		User u = userList.get(0);
		return u;
	}

	@Override
	public User findByUserRole(UserType userRole) {
		Session ses = HibernateUtil.getSession();
		List<User> userList = ses.createQuery("FROM User WHERE userType='" + userRole.name()+"'", User.class).list();
		return userList.get(0);
	}

	// do I need this/ does it work?
	@Override
	public User findByUserRole(String userRole) {
		Session ses = HibernateUtil.getSession();
		List<User> userList = ses.createQuery("FROM User WHERE userType=" + userRole, User.class).list();
		return userList.get(0);
	}

	@Override
	public boolean deleteUser(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		try {
			ses.delete(findByUserID(id));
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
	}

}
