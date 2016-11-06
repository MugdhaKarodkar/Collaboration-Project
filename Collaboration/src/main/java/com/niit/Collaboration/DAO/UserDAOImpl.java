package com.niit.Collaboration.DAO;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.Collaboration.model.User;


@EnableTransactionManagement
@Repository


public class UserDAOImpl implements UserDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl()
	{

	}
	
	public UserDAOImpl(SessionFactory sessionFactory) 
	{
		try 
		{
		  this.sessionFactory = sessionFactory;
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}


	
	@Transactional
	public User get(String user_id) {
		String hql = "from User where user_id ='"+user_id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.list();
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
   
  
@Transactional
public List<User> getList() 
{
	Session session = sessionFactory.openSession();
	List<User> userList = session.createQuery("from User").list();
	session.close();
	return userList;
}
	

	@Transactional
	public boolean save(User user)
	{
		try 
		{
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) 
		{

			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public User authenticate(String user_id,String password) 
	{
		String hql="from User where user_id='"+user_id+"' and "+" password='"+password+"'";
Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<User> list = query.list();
		if (list != null && !list.isEmpty()) 
		{
			return list.get(0);
		} else 
		{
			return null;
		}
		
	}
	@Transactional
	public String update(User user) 
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		Serializable user_id = session.getIdentifier(user);
		session.close();
		return (String) user_id;
	}

	public String deleteRow(String user_id) 
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.load(User.class, user_id);
		session.delete(user);
		tx.commit();
		Serializable ids = session.getIdentifier(user);
		session.close();
		return (String) ids;
	}

	@Transactional
	public User getRowById(String user_id) 
	{
		Session session = sessionFactory.openSession();
		User user = (User) session.load(User.class, user_id);
		return user;
	}
    @Transactional
	public User getByName(String name) 
	{
		String hql = "from User where name =" + " ' " + name + " ' ";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<User> list = query.list();
		if (list == null || list.isEmpty()) 
		{
			return null;
		} else 
		{
			return list.get(0);
		}
	}
}
