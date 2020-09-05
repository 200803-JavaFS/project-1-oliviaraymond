package com.revature.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="users")
public class User implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	public enum UserType {
		EMPLOYEE,
		FINANCIAL_MANAGER
	}
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="user_password", nullable=false)
	private String password;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)	
	private String lastName;
	
	@Column(name="email", unique=true, nullable=false)	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
//	@OneToMany(mappedBy="reimbAuthor", fetch=FetchType.LAZY)
//	private List<Reimbursement> reimbAuthorList;
//	
//	@OneToMany(mappedBy="reimbResolver", fetch=FetchType.EAGER)
//	private List<Reimbursement> reimbResolverList;

	public User() {
		super();
	}

	public User(int userId, String username, String password, String firstName, String lastName, String email,
			UserType userType) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userType = userType;
//		this.reimbAuthorList = reimbAuthorList;
//		this.reimbResolverList = reimbResolverList;
	}

	public User(String username, String password, String firstName, String lastName, String email, UserType userType,
			List<Reimbursement> reimbAuthorList, List<Reimbursement> reimbResolverList) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userType = userType;
//		this.reimbAuthorList = reimbAuthorList;
//		this.reimbResolverList = reimbResolverList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
//
//	public List<Reimbursement> getReimbAuthorList() {
//		return reimbAuthorList;
//	}
//
//	public void setReimbAuthorList(List<Reimbursement> reimbAuthorList) {
//		this.reimbAuthorList = reimbAuthorList;
//	}

//	public List<Reimbursement> getReimbResolverList() {
//		return reimbResolverList;
//	}
//
//	public void setReimbResolverList(List<Reimbursement> reimbResolverList) {
//		this.reimbResolverList = reimbResolverList;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
//		result = prime * result + ((reimbAuthorList == null) ? 0 : reimbAuthorList.hashCode());
//		result = prime * result + ((reimbResolverList == null) ? 0 : reimbResolverList.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
//		if (reimbAuthorList == null) {
//			if (other.reimbAuthorList != null)
//				return false;
//		} else if (!reimbAuthorList.equals(other.reimbAuthorList))
//			return false;
//		if (reimbResolverList == null) {
//			if (other.reimbResolverList != null)
//				return false;
//		} else if (!reimbResolverList.equals(other.reimbResolverList))
//			return false;
		if (userId != other.userId)
			return false;
		if (userType != other.userType)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userType=" + userType
				+ ", reimbAuthorList=" + ", reimbResolverList=" + "]";
	}

//	public User(int userId, String username, String password, String firstName, String lastName, String email,
//			UserType userType) {
//		super();
//		this.userId = userId;
//		this.username = username;
//		this.password = password;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.userType = userType;
//	}

	
}
