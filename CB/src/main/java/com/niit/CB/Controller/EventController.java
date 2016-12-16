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
import com.niit.CB.DAO.EventDAO;
import com.niit.CB.model.Event;

@RestController
public class EventController
{
	@Autowired
	EventDAO eventDAO;

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> listAllEvents() {
		List<Event> event = eventDAO.getList();
		if (event.isEmpty()) {
			return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Event>>(event, HttpStatus.OK);
	}

	// http://localhost:8992/Collaboration/blog/
	@RequestMapping(value = "/event/", method = RequestMethod.POST)

	public ResponseEntity<Event> createBlog(@RequestBody Event event, HttpSession session) {
		if (eventDAO.get(event.getEvent_id()) == null) {
			String loggedInUserID = (String) session.getAttribute("loggedInUserID");
			event.setUser_id(loggedInUserID);
			Date date = new Date();
			long time = date.getTime();
			Timestamp timestamp = new Timestamp(time);
			event.setCreatedAt(timestamp);
			eventDAO.save(event);
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		}
		event.setErrorMessage("Event already exists with ID:" + event.getEvent_id());
		event.setErrorCode("200");
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@RequestMapping(value = "/event/{event_id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable("event_id") String event_id) {
		System.out.println("Fetching Event with id " + event_id);

		Event event = eventDAO.get(event_id);
		if (event == null) {
			event = new Event();
			event.setErrorMessage("Event does not exist with id" + event_id);
			return new ResponseEntity<Event>(event, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

}
