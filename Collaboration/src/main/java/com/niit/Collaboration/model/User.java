package com.niit.Collaboration.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name="USER")
@Component
public class User extends BaseDomain
{   
	 
     @Id
     @Column(name="USER_ID")
     private String user_id;
     
     @NotEmpty
	 @Column(name="NAME")
     private String name;
     
     @NotEmpty
	 @Column(name="PASSWORD")
     private String password;
     
     @NotEmpty
	 @Column(name="CONFIRM_PASSWORD")
     private String confirm_password;
     
     @NotEmpty
	 @Column(name="MAIL")
     private String mail;
     
     @NotEmpty
	 @Column(name="CONTACT")
     private String contact;
     
     @NotEmpty
	 @Column(name="ADDRESS")
     private String address;
     
     

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

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
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

	
     
     
}
