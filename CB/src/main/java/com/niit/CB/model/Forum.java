package com.niit.CB.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "c_forum")
@Component
public class Forum extends BaseDomain {
	
	@Id
	@Column(name="forumId")
	private String forumId;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="forumTitle")
	private String forumTitle;
	
	@Column(name="forumDescription")
	private String forumDescription;
	
	@Column(name="createdAt")
	private Timestamp createdAt;
	
	@Column(name="status")
	private String status;
	public String getForumId() {
		return forumId;
	}
	public void setForumId(String forumId) {
		this.forumId = forumId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getForumTitle() {
		return forumTitle;
	}
	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	public String getForumDescription() {
		return forumDescription;
	}
	public void setForumDescription(String forumDescription) {
		this.forumDescription = forumDescription;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
