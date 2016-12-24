package com.niit.CB.DAO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.CB.DAO.BlogDAO;
import com.niit.CB.model.Blog;

@EnableTransactionManagement
@Repository

public class BlogDAOImpl implements BlogDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAOImpl()
	{

	}
	
	public BlogDAOImpl(SessionFactory sessionFactory) 
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
	public Blog get(String blog_id) {
		
		String hql = "from Blog where blog_id ='"+blog_id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog> list = query.list();
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	@Transactional
	public boolean save(Blog blog) {
		
		try 
		{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (Exception e) 
		{

			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public List<Blog> getList() {
		
		Session session = sessionFactory.openSession();
		List<Blog> blogList = session.createQuery("from Blog").list();
		session.close();
		return blogList;
	}
}

