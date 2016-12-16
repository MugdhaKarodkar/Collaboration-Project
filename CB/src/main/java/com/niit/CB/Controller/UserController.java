package com.niit.CB.Controller;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.CB.DAO.UserDAO;
import com.niit.CB.model.User;

@RestController
public class UserController 
{
   @Autowired
   UserDAO userDAO;
   
   @Autowired
   User user;
   
   
   
   @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession session) {
		//log.debug("Starting of the method logout" );
		
		String loggedInUserID= (String) session.getAttribute("loggedInUserID");
		
		//log.debug("loggedInUserID : " + loggedInUserID);
		
		user =userDAO.get(loggedInUserID);
		
		//log.debug("user:"+ user);
		
		user.setIsOnline("N");

		session.invalidate();
		
		

		if (userDAO.update(user)) 
		{
			user.setErrorCode("200");
			user.setErrorMessage("You have logged out successfully");
		} else 
		{
			user.setErrorCode("404");
			user.setErrorMessage("You could not logged out. Please contact admin");
		}
		//log.debug("Ending of the method logout" );
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

   
   
   //http://localhost:8992/Collaboration/users
   @RequestMapping(value="/users",method=RequestMethod.GET)
   public  ResponseEntity<List<User>> listAllUsers()
   {
	   List<User>user=userDAO.getList();
	   if(user.isEmpty())
	   {
		   return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
	   }
	   return new ResponseEntity<List<User>>(user,HttpStatus.OK);
   }
   //http://localhost:8992/Collaboration/user/
   @RequestMapping(value="/user/",method=RequestMethod.POST)
   
   public ResponseEntity<User> createUser(@RequestBody User user)
   {
	   if(userDAO.get(user.getUser_id())==null)
	   {
		   user.setStatus("N");
		   user.setIsOnline("N");
		   userDAO.save(user);
		   return new ResponseEntity<User>(user,HttpStatus.OK);
	   }
	  
	   user.setErrorMessage("User already exists with ID:"+user.getUser_id());
	   user.setErrorCode("200");
	   return new ResponseEntity<User>(user,HttpStatus.OK);
   }
   
 //http://localhost:8992/Collaboration/user/
   @RequestMapping(value="/user/{user_id}",method=RequestMethod.PUT)
   public ResponseEntity<User> updateUser(@PathVariable("user_id")String user_id,@RequestBody User user)
   {
	   if(userDAO.get(user_id)==null)
	   {
		   user=new User();
		   user.setErrorMessage("User does not exist with ID:"+user.getUser_id());
		   user.setErrorCode("404");
		   return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
	   }
	   //user.setStatus("Y");
	   //user.setIsOnline("Y");  
	   userDAO.update(user);
	   return new ResponseEntity<User>(user,HttpStatus.OK);
   }
   
   //http://localhost:8992/Collaboration/user/user_id
   @RequestMapping(value="/user/{user_id}",method=RequestMethod.DELETE)
   public ResponseEntity<User> deleteUser(@PathVariable("user_id")String user_id)
   {
	  User user=userDAO.get(user_id);
	   
	  if(user==null)
	   {
		   user=new User();
		   user.setErrorMessage("User does not exist with ID:"+user_id);
		   user.setErrorCode("404");
		   return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
	   }
	   
	   userDAO.deleteRow(user_id);
	   return new ResponseEntity<User>(user,HttpStatus.OK);
	   
	   
   }
   @RequestMapping(value = "/user/authenticate/", method = RequestMethod.POST)
   public ResponseEntity<User> authenticate(@RequestBody User user, HttpSession session) {
      user=userDAO.authenticate(user.getUser_id(),user.getPassword());
      if(user==null)
      {
    	  user=new User();
    	  user.setErrorMessage("Invalid credentials..Please enter valid credentials");
    	  return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
      }
      else
      {
    	  user.setIsOnline("Y");
    	  //user.setStatus("Y");
    	  userDAO.update(user);
    	  session.setAttribute("loggedInuser", user);
    	  session.setAttribute("loggedInUserID", user.getUser_id());
      }
      return new ResponseEntity<User>(user,HttpStatus.OK);
   }
   
   @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
   public ResponseEntity<User> getUser(@PathVariable("user_id") String user_id) {
       System.out.println("Fetching User with id " + user_id);
       
       User user = userDAO.get(user_id);
       if (user == null) 
       {
    	   user=new User();
    	   user.setErrorMessage("User does not exist with id"+user_id);
           return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<User>(user, HttpStatus.OK);
   }
}
