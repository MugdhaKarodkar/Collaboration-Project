import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.Collaboration.DAO.TJobDAO;
import com.niit.Collaboration.model.Job;


public class JobTest 
{
	
	
	public static void main(String[] args)
	{
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
        TJobDAO tJobDAO = (TJobDAO) context.getBean("tJobDAO");
        Job job = (Job) context.getBean("job");
        job.setJob_id("002");
		job.setTitle("JAVA Developer requirement");
		job.setStatus('V');
		job.setQualification("BE/BTech");
		job.setDescription("We have vacancies for JAVA Developer.We need technical person having good knowledge of JAVA,good communication skills,aggregate first class in graduation");
		job.setDateTime("03/11/2016");
		if (tJobDAO.postJob(job) == true)
		{
			System.out.println("Job successfully posted");
		} 
		else
		{
			System.out.println("job not able to post");
		}
	}
}

