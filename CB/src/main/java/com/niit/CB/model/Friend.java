package com.niit.CB.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table(name="C_FRIEND")
@Component
public class Friend extends BaseDomain
{
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="user_id")
	private String userID;
	
	@Column(name="friend_id")
	private String friendID;
	
	//New, accepted, rejected
	private String status;
	
	
	/*@Column(name="is_online")
	private char  isOnline;*/

    public String getId() 
	{
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFriendID() {
		return friendID;
	}

	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
