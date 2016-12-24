package com.niit.CB.DAO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.niit.CB.model.Forum;
@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO {
	@Autowired
	SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdateForum(Forum forum) {
		sessionFactory.getCurrentSession().save(forum);

	}
	@Transactional
	public Forum getForum(String forumId) {
		String hql = "from Forum where forumId ='"+forumId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Forum> list = query.list();
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	@Transactional
	public List<Forum> listForums() {
		
		Session session = sessionFactory.openSession();
		List<Forum> forumList = session.createQuery("from Forum").list();
		session.close();
		return forumList;
	}


	@Transactional
	public void deleteForum(String forumId) {
		Forum forumToDelete = new Forum();
		forumToDelete.setForumId(forumId);
		sessionFactory.getCurrentSession().delete(forumToDelete);
	}

	
	
	
	@Transactional
	public List<Forum> listForumByCreatedAt(char status){
		String hql = "from Forum where status=:status order by createdAt desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("status", status);
		List<Forum> listOfForums = query.getResultList();
		return listOfForums;
	}
	

}
