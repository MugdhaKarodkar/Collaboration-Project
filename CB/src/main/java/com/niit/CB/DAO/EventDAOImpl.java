package com.niit.CB.DAO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.CB.model.Event;

@EnableTransactionManagement
@Repository

public class EventDAOImpl implements EventDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public EventDAOImpl()
	{

	}
	
	public EventDAOImpl(SessionFactory sessionFactory) 
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
	public Event get(String event_id) {
		
		String hql = "from Event where event_id ='"+event_id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Event> list = query.list();
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	@Transactional
	public boolean save(Event event) {
		
		try 
		{
			sessionFactory.getCurrentSession().save(event);
			return true;
		} catch (Exception e) 
		{

			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public List<Event> getList() {
		
		Session session = sessionFactory.openSession();
		List<Event> eventList = session.createQuery("from Event").list();
		session.close();
		return eventList;
	}
}
