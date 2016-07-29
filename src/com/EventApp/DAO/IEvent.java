package com.EventApp.DAO;

import java.util.List;

import com.EventApp.TO.EventTO;
import com.EventApp.TO.StudentTO;

public interface IEvent {
	public void createEvent(EventTO eventto);
	public void assignEvent(EventTO eventto);
	public List<EventTO> fetchEvent();
	public boolean fetchEvent(EventTO eventto, StudentTO studentto);
	public void updateEvent(EventTO eventto);
	public void deleteEvent(EventTO eventto);
	public List<String> checkBatch(EventTO eventto);
	public List<EventTO> fetchEventBatch();
	public List<String> fetchBatch();
}
