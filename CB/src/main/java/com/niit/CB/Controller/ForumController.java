package com.niit.CB.Controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.CB.DAO.ForumDAO;
import com.niit.CB.model.Blog;
import com.niit.CB.model.Forum;



@RestController
public class ForumController {
	
	@Autowired 
	ForumDAO forumDAO;
	@Autowired
	Forum forum;
	
	
	@RequestMapping(value = "/forums", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> listAllForums() {
		List<Forum> forum = forumDAO.listForums();
		if (forum.isEmpty()) {
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/forum/", method = RequestMethod.POST)
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum,HttpSession session){
		
		if (forumDAO.getForum(forum.getForumId())== null)
		{
			String loggedInUserID = (String) session.getAttribute("loggedInUserID");
			forum.setUserId(loggedInUserID);
			forum.setStatus("P");
			Date date = new Date();
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			forum.setCreatedAt(timestamp);
			forumDAO.saveOrUpdateForum(forum);
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		forum.setErrorMessage("Forum already exists with ID:" + forum.getForumId());
		forum.setErrorCode("200");
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
}
	
	/*@PutMapping("/forums/{forumId}")
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum,@PathVariable("forumId")String forumId){
		this.forum=forumDAO.getForum(forumId);
		if(this.forum == null){
			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
		}
		this.forum.setForumTitle(forum.getForumTitle());
		this.forum.setForumDescription(forum.getForumDescription());
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		this.forum.setModifiedAt(timestamp);
		forumDAO.saveOrUpdateForum(this.forum);
		return new ResponseEntity<Forum>(this.forum,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/forums/{forumId}")
	public ResponseEntity<Forum> deleteforum(@PathVariable("forumId")String forumId){
		if(forumDAO.getForum(forumId)==null)
		{
			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
		}
		
		forumDAO.deleteForum(forumId);
		return new ResponseEntity<Forum>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/forums/{forumId}")
	public ResponseEntity<Forum> getForum(@PathVariable("forumId")String forumId){
		forum=forumDAO.getForum(forumId);
		if(forum==null){
			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}*/
	

}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	