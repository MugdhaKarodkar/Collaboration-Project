package com.niit.Collaboration.DAO;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.Collaboration.model.Job;
import com.niit.Collaboration.model.Jobapplication;
import com.niit.Collaboration.model.User;


@EnableTransactionManagement
@Repository("tJobDAO")

public class TJobDAOImpl implements TJobDAO {

	private SessionFactory sessionFactory;

	public TJobDAOImpl() {

	}

	public TJobDAOImpl(SessionFactory sessionFactory) {
		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	@Transactional
	public boolean postJob(Job job)
	{
		try 
		{
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (Exception e) 
		{

			e.printStackTrace();
			return false;
		}
	}
	 @Transactional
		public List<Job> getAllVacantJobs() {
			String hql = "from Job where status='V'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			return query.list();
		}
	 
	 
		
		@Transactional
		public Job get(String job_id) {
			String hql = "from Job where job_id ='"+job_id+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			List<Job> list = query.list();
			if (list == null || list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}
		}
	 
    @Transactional
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
   
    @Transactional
	public boolean applyForJob(Jobapplication jobapplication) {

		try {
			sessionFactory.getCurrentSession().save(jobapplication);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
    @Transactional
	public boolean updateJob(Jobapplication jobapplication) {

		try {
			sessionFactory.getCurrentSession().update(jobapplication);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
    @Transactional
	public Jobapplication get(String userId, String jobId) {

		String hql = "from Jobapplication where user_id='" + userId + "' and " + " jobID='" + jobId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (Jobapplication) query.list();
	}
    @Transactional
	public Jobapplication getMyAppliedJobs(String userId) {
		String hql = "from Job where job_id in(select jobAppID from Jobapplication where user_id='userId')";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (Jobapplication) query.list();
	}
    /*@Transactional
    public Jobapplication getJobApplication(String job_id)
    {
    	String hql="from Jobapplication where jobID"
    }*/

}
