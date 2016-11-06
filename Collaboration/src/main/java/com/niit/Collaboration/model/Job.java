package com.niit.Collaboration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Table(name = "Job")
@Entity
@Component

public class Job extends BaseDomain {
	@Id
	@Column(name = "job_id")
	private String job_id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "qualification")
	private String qualification;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "date_time")
	// private Date dateTime;
	private String dateTime;

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		/*
		 * if(dateTime==null) { new Date(System.currentTimeMillis()); }
		 */
		this.dateTime = dateTime;
	}

}
