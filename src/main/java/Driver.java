
import java.sql.Timestamp;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;
import com.revature.models.User;
import com.revature.models.User.UserType;

public class Driver {

	public static UserDAO userDao = new UserDAO();
	public static ReimbursementDAO rDao = new ReimbursementDAO();

	public static void main(String[] args) {

		insertValues();

		List<User> users = userDao.findAll();

		for (User u : users) {
			System.out.println(u);
		}

		// User u= userDao.findByUsername("mom");
		// User u= userDao.findByUserID(2);
		// User u= userDao.findByCredentials("mom", "target");

		// not working
		// User u= userDao.findByUserRole("EMPLOYEE");

		// not working
//		Boolean u = userDao.deleteUser(2); 
//		
//		List<User> users2 = userDao.findAll();
//		
//		for(User u1 : users2) {
//			System.out.println(u1);
//		}

//		System.out.println(u.getPassword());
//		System.out.println(u.getFirstName());

//	List<Reimbursement> reimbs = rDao.findAll();
//		
//		for(Reimbursement r : reimbs) {
//			System.out.println(r);
//		}
//		User u= userDao.findByUsername("livray");
//		List<Reimbursement> r= rDao.findReimbursementsByUser(u);
//		for(Reimbursement r1 : r) {
//			System.out.println(r1);
//		}
//		User user = userDao.findByUserID(2);
//		user.setEmail("mom.gmail");
//		userDao.updateUser(user);
//		Reimbursement reimb = rDao.findByReimID(2);
//		reimb.setReimbAmount(3);
//		rDao.updateReimbursement(reimb);
//		User user = userDao.findByUserRole(UserType.EMPLOYEE);
//		System.out.println(user);
//		User user = userDao.findByUserID(6);
//		List<Reimbursement> reimb = rDao.findReimbursementByStatus(ReimbursementStatus.APPROVED);
//		System.out.println(reimb);
//		
	}

	public static void insertValues() {
		
	
		
//		User user1= new User("livray", hash("soccer"), "Olivia", "Raymond", "livray@gmail.com",UserType.EMPLOYEE);
//		User user2= new User("mom", hash("target"), "Martha", "Ames", "ames@gmail.com", UserType.FINANCIAL_MANAGER);
//		userDao.addUser(user1);
//		userDao.addUser(user2);
//				
//		userDao.addUser(user1);
//		userDao.addUser(user2);
//		
//		Reimbursement reimb1 = new Reimbursement(30.00, new Timestamp(System.currentTimeMillis()), null, "mowed the lawn", user1, user2, ReimbursementStatus.APPROVED, ReimbursementType.OTHER);
//		rDao.addReimbursement(reimb1);
//		
//		
//		userDao.updateUser(user1);
//		
//
//		User user3 = new User("dad", hash("google"), "Rob", "Raymond", "raymond@gmail.com", UserType.FINANCIAL_MANAGER);
//		userDao.addUser(user3);
		// userDao.deleteUser(3);
		// rDao.deleteReimbursement(1);
		// System.out.println("deleting");

		// rDao.deleteReimbursement(1);
	
	}

	private static String hash(String pass) {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");

			byte[] hashByte = md.digest(pass.getBytes(StandardCharsets.UTF_8));
			String myHash = DatatypeConverter.printHexBinary(hashByte);
			return (myHash);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;

	}

}
