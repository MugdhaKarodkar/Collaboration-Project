package com.niit.Collaboration.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Table(name="Jobapplication")
@Entity
@Component
public class Jobapplication {
	@Id
	@Column(name = "jobAppID")
	private String jobAppID;

	@Column(name = "user_id")
	private String user_id;

	@Column(name = "jobID")
	private String jobID;

	@Column(name = "Date_Applied")
	//private Date Date_Applied;
    private String Date_Applied;
	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private char status;

	public String getJobAppID() {
		return jobAppID;
	}

	public void setJobAppID(String jobAppID) {
		this.jobAppID = jobAppID;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public String getDate_Applied() {
		return Date_Applied;
	}

	public void setDate_Applied(String date_Applied) {
		Date_Applied = date_Applied;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
