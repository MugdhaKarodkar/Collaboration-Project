package com.niit.Collaboration.DAO;
import java.util.List;
import com.niit.Collaboration.model.Job;
import com.niit.Collaboration.model.Jobapplication;
import com.niit.Collaboration.model.User;

public interface TJobDAO 
{
    public boolean postJob(Job job);
    public List<Job> getAllVacantJobs();
    public Job get(String job_id);
    public boolean updateJob(Job job);
    public boolean applyForJob(Jobapplication jobapplication);
    public boolean updateJob(Jobapplication jobapplication);
    public Jobapplication get(String userId,String jobId);
    public Jobapplication getMyAppliedJobs(String userId);
    //public Jobapplication getJobApplication(String job_id);
}
