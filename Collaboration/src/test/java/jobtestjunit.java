import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.Collaboration.DAO.TJobDAO;
import com.niit.Collaboration.model.Job;

public class jobtestjunit {

	@Autowired(required=true)
	TJobDAO tJobDAO;

	@Autowired(required=true)
	Job job;
	
	
	AnnotationConfigApplicationContext context;
	
	
	@Before
	public void init() 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		tJobDAO = (TJobDAO) context.getBean("tJobDAO");
	    job = (Job) context.getBean("job");
	}

	//@Test
	public void jobListTestCase() {
		assertEquals("listjobTestCase", tJobDAO.getAllVacantJobs().size(), 1);
	}
	@Test
	public void jobAddTestcase() {
			job.setJob_id("001");
			job.setTitle("JAVA Developer requirement");
			job.setStatus('V');
			job.setQualification("BE/BTech");
			job.setDescription("We have vacancies for JAVA Developer.We need technical person having good knowledge of JAVA,good communication skills,aggregate first class in graduation");
			job.setDateTime("03/11/2016");
			tJobDAO.postJob(job);
			assertEquals("userAddTestCase",tJobDAO.postJob(job), true);

		}

}
