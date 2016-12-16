package com.niit.applicationconfig;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.niit.CB.DAO.BlogDAO;
import com.niit.CB.DAO.BlogDAOImpl;
import com.niit.CB.DAO.EventDAO;
import com.niit.CB.DAO.EventDAOImpl;
import com.niit.CB.DAO.ForumDAO;
import com.niit.CB.DAO.ForumDAOImpl;
import com.niit.CB.DAO.FriendDAO;
import com.niit.CB.DAO.FriendDAOImpl;
import com.niit.CB.DAO.UserDAO;
import com.niit.CB.DAO.UserDAOImpl;
import com.niit.CB.model.Blog;
import com.niit.CB.model.Event;
import com.niit.CB.model.Forum;
import com.niit.CB.model.Friend;
import com.niit.CB.model.User;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
//@EnableWebMvc


//public class ApplicationContextConfig extends WebMvcConfigurerAdapter
public class ApplicationContextConfig
{  
	@Bean(name = "dataSource")
	public DataSource getDataSource() 
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/CB");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	private Properties getHibernateProperties() 
	{
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		
		sessionBuilder.addAnnotatedClass(User.class);
		//sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) 
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}
	
	
	 @Autowired
	 @Bean(name = "userDAO")
	 public UserDAO getUserDAO(SessionFactory sessionFactory)
	 {
	    return new UserDAOImpl(sessionFactory);
	 }
	 @Autowired
	 @Bean(name = "blogDAO")
	 public BlogDAO getBlogDAO(SessionFactory sessionFactory)
	 {
	    return new BlogDAOImpl(sessionFactory);
	 }
	 @Autowired
	 @Bean(name = "forumDAO")
	 public ForumDAO getForumDAO(SessionFactory sessionFactory)
	 {
	    return new ForumDAOImpl(sessionFactory);
	 }
	 @Autowired
	 @Bean(name = "friendDAO")
	 public FriendDAO getFriendDAO(SessionFactory sessionFactory)
	 {
	    return new FriendDAOImpl(sessionFactory);
	 }
	 @Autowired
	 @Bean(name = "eventDAO")
	 public EventDAO getEventDAO(SessionFactory sessionFactory)
	 {
	    return new EventDAOImpl(sessionFactory);
	 }
	 
	/* @Autowired
	 @Bean(name = "tJobDAO")
	 public TJobDAO getTJobDAO(SessionFactory sessionFactory)
	 {
	    return new TJobDAOImpl(sessionFactory);
	 }*/
	
	 
	/* @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }*/
	
}
