package com.niit.CB.model;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import com.niit.CB.model.BaseDomain;

@Entity
@Table(name = "c_blog")
@Component
public class Blog extends BaseDomain  {
	@Column(name = "blog_id")
	@Id
	private String blog_id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "user_id")
	private String user_id;
	
	@Column(name="createdAt")
	private Timestamp createdAt;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "reason")
	private String reason;

	public String getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
