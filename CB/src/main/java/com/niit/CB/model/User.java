package com.niit.CB.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "USER")
@Component
public class User extends BaseDomain {

	@Id
	@Column(name = "USER_ID")
	private String user_id;

	@NotEmpty
	@Column(name = "NAME")
	private String name;

	@NotEmpty
	@Column(name = "PASSWORD")
	private String password;

	@NotEmpty
	@Column(name = "MAIL")
	private String mail;

	@NotEmpty
	@Column(name = "CONTACT")
	private String contact;

	@NotEmpty
	@Column(name = "ADDRESS")
	private String address;

	@NotEmpty
	@Column(name = "ROLE")
	private String role;

	//@NotEmpty
	@Column(name = "ISONLINE")
	private String isOnline;

	//@NotEmpty
	@Column(name = "STATUS")
	private String status;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	

	

	
}
