package com.niit.Collaboration.Controller;
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
import com.niit.Collaboration.DAO.UserDAO;
import com.niit.Collaboration.model.User;

@RestController
public class UserController 
{
   @Autowired
   UserDAO userDAO;
   
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
