package com.revature.services;

import java.sql.Timestamp;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.revature.models.LoginDTO;
import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;
import com.revature.models.ReimbursementDTO;
import com.revature.models.User;
import com.revature.models.User.UserType;
import com.revature.services.LoginService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

//how to figure out test coverage of just service? when to use the different asserts?

public class ServiceTest {

	// initialize services
	public static LoginService ls;
	public static UserService us;
	public static ReimbursementService rs;

	public static User testUser;
	public static ReimbursementDTO testReimb;
	public static Reimbursement testReimb2;

	public ServiceTest() {
		super();
	}

	@BeforeClass
	public static void set() {
		System.out.println("In Before Class...");
		ls = new LoginService();
		us = new UserService();
		rs = new ReimbursementService();

	}

	@Before
	public void startup() {
		testUser = new User("testUsername", "testPassword", "testFirstName", "testLastName", "test@test.com",
				UserType.EMPLOYEE);
		testReimb2 = new Reimbursement(100.20, new Timestamp(System.currentTimeMillis()), null, "testDescription",
				us.findByUsername("mom"), null, ReimbursementStatus.PENDING, ReimbursementType.OTHER);
	}

	@Test
	public void addUserTest() {
		boolean userAdd = us.addUser(testUser);
		assertEquals(true, userAdd);
	}

	@Test
	public void addReimbTest() {
		boolean reimbAdd = rs.addReimbursement(testReimb);
		assertTrue(reimbAdd);
	}

	@Test
	public void findAll() {
		List<User> u = us.findAll();
		assertNotNull(u);
	}

	@Test
	public void updateUser() {
		boolean b = us.updateUser(testUser);
		assertTrue(b);
	}

	@Test
	public void findByRole() {
		User u = us.findByRole(UserType.EMPLOYEE);
		assertNotNull(u);
	}

	@Test
	public void deleteUser() {
		boolean b = us.deleteUser(4);
		assertTrue(b);
	}

	@Test
	public void getReimbById() {
		Reimbursement r = rs.findById(1);
		System.out.println(r);
		Reimbursement rTestAgainst = new Reimbursement(5, 4.5, new Timestamp(System.currentTimeMillis()), null,
				"testDescriptionAgainst", us.findByUsername("mom"), null, ReimbursementStatus.APPROVED,
				ReimbursementType.OTHER);
		System.out.println(rTestAgainst);
		assertEquals(r, rTestAgainst);
	}

	@Test
	public void findUserById() {
		System.out.println("In find by user ID");
		User u = us.findById(4);
		System.out.println(u.getPassword());
		User testAgainst = new User("testUsername", "testPassword", "testFirstName", "testLastName", "test@test.com",
				UserType.EMPLOYEE);
		assertEquals(u, testAgainst);
	}

	@Test
	public void findUserByUsername() {
		System.out.println("In find by username");
		User u = us.findByUsername("testUsername");
		System.out.println(u.getPassword());
		User testAgainst = new User(15, "henry", u.getPassword(), "Henry", "Raymond", "henhen@gmail.com",
				UserType.FINANCIAL_MANAGER);
		assertEquals(u, testAgainst);
	}

	public void findUserByCredentials() {
		System.out.println("In find by credentials");
		User u = us.findByUserPassword("testUsername", "testPassword");
		System.out.println(u.getPassword());
		User testAgainst = new User("testUsername", "testPassword", "testFirstName", "testLastName", "test@test.com",
				UserType.EMPLOYEE);
		assertEquals(u, testAgainst);
	}

	@Test
	public void findReimbByUser() {
		User u = us.findById(testUser.getUserId());
		List<Reimbursement> rList = rs.findByUser(u);
		assertTrue(rList != null);
	}

	@Test
	public void findAllReimb() {
		List<Reimbursement> rList = rs.findAll();
		assertNotNull(rList);
	}

	@Test
	public void findReimbByStatus() {
		List<Reimbursement> rListStatus = rs.findByStatus(ReimbursementStatus.APPROVED);
		assertNotNull(rListStatus);
	}

	@Test
	public void findReimbByType() {
		List<Reimbursement> rListType = rs.findByType(ReimbursementType.OTHER);
		assertNotNull(rListType);
		// thats going to be null when there arent any travel types... how to do this
		// better?
	}

	// add the rest of the methods in the service layer (reimb service and user
	// service)

//	@Test
//	public void testLogin() {
//		System.out.println("In login...");
//		LoginDTO u = new LoginDTO("testUsername", "testPassword");
//		System.out.println(u);
//		boolean loginBool = ls.login(u);
//		assertTrue(loginBool);
//	}
	
//	@Test 
//	public void getUserType() {
//		LoginDTO u = new LoginDTO("testUsername", "testPassword");
//		System.out.println(u);
//		boolean loginBool = ls.login(u);
//		assertTrue(loginBool);
//		
//	}

	@AfterClass
	public static void shutdown() {
		testUser = null;
		testReimb2 = null;
		ls = null;
		us = null;
		rs = null;
	}

}
