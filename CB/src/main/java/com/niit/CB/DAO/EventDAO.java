package com.niit.CB.DAO;
import java.util.List;
import com.niit.CB.model.Event;
public interface EventDAO 
{
	public Event get(String event_id);
	public boolean save(Event event);
    public List<Event> getList();
}
