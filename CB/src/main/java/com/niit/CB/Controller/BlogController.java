package com.niit.CB.Controller;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.CB.DAO.BlogDAO;
import com.niit.CB.model.Blog;

@RestController
public class BlogController {
	@Autowired
	BlogDAO blogDAO;

	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> listAllBlogs() {
		List<Blog> blog = blogDAO.getList();
		if (blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}

	// http://localhost:8992/Collaboration/blog/
	@RequestMapping(value = "/blog/", method = RequestMethod.POST)

	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, HttpSession session) {
		if (blogDAO.get(blog.getBlog_id()) == null) {
			String loggedInUserID = (String) session.getAttribute("loggedInUserID");
			blog.setUser_id(loggedInUserID);
			blog.setStatus('P');
			Date date = new Date();
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			blog.setCreatedAt(timestamp);
			blogDAO.save(blog);
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		blog.setErrorMessage("Blog already exists with ID:" + blog.getBlog_id());
		blog.setErrorCode("200");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value = "/blog/{blog_id}", method = RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable("blog_id") String blog_id) {
		System.out.println("Fetching Blog with id " + blog_id);

		Blog blog = blogDAO.get(blog_id);
		if (blog == null) {
			blog = new Blog();
			blog.setErrorMessage("Blog does not exist with id" + blog_id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

}
