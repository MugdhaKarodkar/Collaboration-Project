package com.niit.CB.DAO;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.niit.CB.model.Friend;

@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {
	// private static final Logger log =
	// LoggerFactory.getLogger(UserDAOImpl.class);
	
	

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e) {
			// log.error(" Unable to connect to db");
			e.printStackTrace();
		}
	}

	private Integer getMaxId() {
		// log.debug("->->Starting of the method getMaxId");
        
		String hql = "select max(id) from Friend";
		Query query = sessionFactory.openSession().createQuery(hql);
		Integer maxID;
		try {
			maxID = (Integer) query.uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		// log.debug("Max id :"+maxID);
		return maxID;

	}

	@Transactional
	public boolean save(Friend friend) {

		try {
			// log.debug("*********************88Previous id "+getMaxId());
			//friend.setId(getMaxId() + 1);
			friend.setId(friend.getFriendID());
			
			//friend.setId(friend.getUserID());
			// log.debug("***********************generated id:"+getMaxId());
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean update(Friend friend) {

		try {
			sessionFactory.openSession().update(friend);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public void delete(String userID, String friendID) {
		Friend friend = new Friend();
		friend.setFriendID(friendID);
		friend.setUserID(userID);
		sessionFactory.openSession().delete(friend);
	}

	@Transactional
	public List<Friend> getMyFriends(String userID) {
		String hql = "from Friend where userID=" + "'" + userID + "' and status ='" + "A'";
		Query query = sessionFactory.openSession().createQuery(hql);

		List<Friend> list = (List<Friend>) query.list();

		return list;

	}

	@Transactional
	public List<Friend> getNewFriendRequests(String userID) {
		String hql = "from Friend where userID=" + "'" + userID + "' and status ='" + "N'";
		Query query = sessionFactory.openSession().createQuery(hql);

		List<Friend> list = (List<Friend>) query.list();

		return list;

	}

	@Transactional
	public Friend get(String userID, String friendID) {
		String hql = "from Friend where userID=" + "'" + userID + "' and friendID= '" + friendID + "'";

		// log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);

		return (Friend) query.uniqueResult();

	}

	// We can use update method also.
	// isOnline need to set in controller and the ncal update

	/**
	 * Instead of writing the below two methods, you can use update method
	 */

	@Transactional
	public void setOnline(String userID) {
		// log.debug("Starting of the metnod setOnline");
		String hql = " UPDATE Friend	SET isOnline = 'Y' where friendID='" + userID + "'";
		// log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();
		// log.debug("Ending of the metnod setOnline");

	}

	@Transactional
	public void setOffLine(String userID) {
		// log.debug("Starting of the metnod setOffLine");
		String hql = " UPDATE Friend	SET isOnline = 'N' where friendID='" + userID + "'";
		// log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		query.executeUpdate();
		// log.debug("Ending of the method setOffLine");

	}

}
