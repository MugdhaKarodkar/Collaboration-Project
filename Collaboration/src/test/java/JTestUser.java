import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.Collaboration.DAO.UserDAO;
import com.niit.Collaboration.model.User;

//@WebAppConfiguration
public class JTestUser {

	@Autowired(required = true)
	UserDAO userDAO;

	@Autowired(required = true)
	User user;

	AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com");

		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
		user = (User) context.getBean("user");
	}

	// @Test
	public void userListTestCase() {
		assertEquals("listuserTestCase", userDAO.getList().size(), 3);
	}

	// @Test
	public void userAddTestcase() {
		user.setUser_id("mugdha");
		user.setName("mugdha");
		user.setPassword("mugdha");
		user.setConfirm_password("mugdha");
		user.setMail("mugdha@gmail.com");
		user.setAddress("pune");
		user.setContact("9763338707");
		userDAO.save(user);
		assertEquals("userAddTestCase", userDAO.save(user), true);

	}

	// @Test
	public void userGetTestCase() {

		assertEquals("userGetTestCase", userDAO.get("001"), null);

	}

	// @Test // this is test case method
	public void deleteUserTestCase() {

		// user.setUser_id("asha");
		assertEquals("deleteUserTestCase", userDAO.deleteRow("0010"), null);

	}

	// @Test
	public void userUpdateTestcase() {
		user.setUser_id("001");
		user.setName("Rihan1");
		user.setPassword("rihan");
		user.setConfirm_password("rihan");
		user.setMail("rihan@gmail.com");
		user.setAddress("mumbai");
		user.setContact("9763338790");
		userDAO.update(user);
		assertEquals("userUpdateTestCase", userDAO.update(user), true);

	}

}