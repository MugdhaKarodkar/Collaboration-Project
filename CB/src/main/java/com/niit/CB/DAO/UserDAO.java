package com.niit.CB.DAO;

import java.util.List;
import com.niit.CB.model.User;

//@Component
public interface UserDAO {
	public User get(String user_id);
	
	

	public boolean save(User user);
	public List<User> getList();

	//public String update(User user);
	public boolean update(User user);
	public User authenticate(String user_id,String password); 

	public String deleteRow(String user_id);

	

	public User getRowById(String user_id);

	public User getByName(String name);
	
	
}
