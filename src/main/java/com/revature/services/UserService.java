package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IUserDAO;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.models.User.UserType;

public class UserService {

	private static final Logger log = LogManager.getLogger(UserService.class);
	private static IUserDAO uDao= new UserDAO();
	
	//use format specifiers for better logging messages
	
	public List<User> findAll(){
		log.info("Retrieving all users");
		return uDao.findAll();
	}

	public User findById(int id) {
		log.info("Finding User with id "+ id);
		return uDao.findByUserID(id);
	}
	
	public User findByUserPassword(String username, String password) {
		log.info("Finding User with username: "+ username + " and password: " + password);
		return uDao.findByCredentials(username, password);
	}

	public User findByUsername(String username) {
		log.info("Finding User with username: "+ username);
		return uDao.findByUsername(username);
	}
	
	public boolean addUser(User u) {
		log.info("Adding User: "+ u);
		return uDao.addUser(u);
	}
	
	public boolean updateUser(User u) {
		log.info("Updating User: "+ u);
		return uDao.updateUser(u);
	}
	public User findByRole(UserType role){
		log.info("Retrieving all users with this role");
		return uDao.findByUserRole(role);
	}
	public boolean deleteUser(int id){
		log.info("Deleting user");
		return uDao.deleteUser(id);
	}

}
