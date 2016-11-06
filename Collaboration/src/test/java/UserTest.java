import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.niit.Collaboration.DAO.UserDAO;
import com.niit.Collaboration.model.User;


public class UserTest 
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = (User) context.getBean("user");
		user.setUser_id("002");
		user.setName("mugdha");
		user.setPassword("mugdha");
		user.setConfirm_password("mugdha");
		user.setMail("mugdha@gmail.com");
		user.setContact("9763338707");
		user.setAddress("pune");
		if (userDAO.save(user) == true)
		{
			System.out.println("user successfully created");
		} 
		else
		{
			System.out.println("user not able to create");
		}
	}
}
