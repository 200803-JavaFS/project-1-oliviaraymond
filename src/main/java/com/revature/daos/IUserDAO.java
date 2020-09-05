package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.User.UserType;

public interface IUserDAO {

	public List<User> findAll();

	public User findByUserID(int id);

	public User findByUsername(String username);

	public boolean addUser(User u);

	public boolean updateUser(User u); 
	//update: username, password, userrole, etc.
	
	public boolean deleteUser(int id);

	public User findByCredentials(String username, String password);
	
	public User findByUserRole(UserType userType);

}
