package com.niit.Collaboration.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.Collaboration.DAO.TJobDAO;
import com.niit.Collaboration.model.Job;
import com.niit.Collaboration.model.Jobapplication;


@RestController
public class TJobController {
	@Autowired
	TJobDAO tJobDAO;
	
	@Autowired
	Jobapplication jobapplication;

	@SuppressWarnings("null")
	@RequestMapping(value = "/getAllJobs", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getAllJobs() {
		List<Job> jobs = tJobDAO.getAllVacantJobs();
		if (jobs == null) {
			Job job = new Job();
			job.setErrorCode("404");
			job.setErrorMessage("No jobs are available");
			jobs.add(job);

		}
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	@RequestMapping(value = "/postAJob/", method = RequestMethod.POST)
	public ResponseEntity<Job> postJobs(@RequestBody Job job) 
	{
		if (tJobDAO.postJob(job) == true) 
		{
			job.setErrorCode("200");
			job.setErrorMessage("job successfully posted");
		} else 
		{
			job.setErrorCode("400");
			job.setErrorMessage("Not able to post the job");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);

	}

	   @RequestMapping(value="/updateAJob/{job_id}",method=RequestMethod.PUT)
	   public ResponseEntity<Job> updateJob(@PathVariable("job_id")String job_id,@RequestBody Job job)
	   {
		   if(tJobDAO.get(job_id)==null)
		   {
			   job=new Job();
			   job.setErrorMessage("Job does not exist with ID:"+job.getJob_id());
			   job.setErrorCode("404");
			   return new ResponseEntity<Job>(job,HttpStatus.NOT_FOUND);
		   }
		   
		   tJobDAO.updateJob(job);
		   return new ResponseEntity<Job>(job,HttpStatus.OK);
	   }
	   
	   /*@RequestMapping(value="/applyforAJob/{job_id}",method=RequestMethod.PUT)
	   public ResponseEntity<Job> applyforJob(@PathVariable("job_id")String job_id,HttpSession httpSession)
	   {
		   String loggedInUserId=(String) httpSession.getAttribute("loggedInUserId");
		   jobapplication=tJobDAO.getJobApplication(job_id);
		   
		   
	   }*/
	   
	   
	
}
