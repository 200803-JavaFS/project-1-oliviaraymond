

import java.util.List;

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
		
//		List<User> users = userDao.findAll();
//		
//		for(User m : users) {
//			System.out.println(m);
//		}
//	

	}
	
	public static void insertValues() {
		User user1= new User("livray", "soccer", "Olivia", "Raymond", "livray@gmail.com",UserType.EMPLOYEE, null, null);
//		Director dir1 = new Director("Christopher", "Nolen", null);
//		Director dir2 = new Director("Steven", "Speilburgh", null);
//		Director dir3 = new Director("Wes", "Anderson", null);
//		dirDao.insert(dir1);
//		dirDao.insert(dir2);
//		dirDao.insert(dir3);
		userDao.addUser(user1);
//		
//		Movie m1 = new Movie("Jurassic Park", "Don't feed the lizards", dir2);
//		Movie m2 = new Movie("Dark Knight Rise", "I mean DC is trying right?", dir1);
//		Movie m3 = new Movie("Moon Rise Kingdom", "I have no idea", dir3);
//		Movie m4 = new Movie("ET", "Always be nice to strangers", dir2);
//		mDao.insert(m1);
//		mDao.insert(m2);
//		mDao.insert(m3);
//		mDao.insert(m4);
//		
//		Character c1 = new Character("T-Rex", "female", "hunger");
//		Character c2 = new Character("Batman", "male", "justice");
//		charDao.insert(c1);
//		charDao.insert(c2);
	}

}
