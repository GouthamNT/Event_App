package com.EventApp.BO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.EventApp.Constants.ErrorConstants;
import com.EventApp.Constants.KeywordConstants;
import com.EventApp.DAO.EventDAO;
import com.EventApp.DAO.IEvent;
import com.EventApp.Exception.EventException;
import com.EventApp.TO.EventTO;
import com.EventApp.TO.StudentTO;

public class EventBO {

	IEvent eventdao = new EventDAO();
	Map<String,String> eventerrormap = new HashMap<String,String>();

	public void createEvent(EventTO eventto) throws EventException {
		boolean flag1 = checkDate(eventto.getDate());
		//boolean flag2 = eventdao.checkBatch(eventto);

		if(!flag1) {
			eventerrormap.put(ErrorConstants.EVENT_DATE_ERROR, ErrorConstants.EVENT_DATE_ERROR_MESSAGE);
			throw new EventException(eventerrormap);
		}
		/*if(!flag2) {
			eventerrormap.put(ErrorConstants.EVENT_DATE_BATCH_ERROR, ErrorConstants.EVENT_DATE_BATCH_ERROR_MESSAGE);
		}*/
		else {
			eventdao.createEvent(eventto);
		}
	}

	private boolean checkDate(String date) {
		boolean dateflag = false;
		Date dateformat;
		Date currentdate = new Date();
		long input,current;
		System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			dateformat = sdf.parse(date);
			input = dateformat.getTime();
			current = currentdate.getTime();
			System.out.println(input);
			System.out.println(current);
			if(current > input) {
				dateflag = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateflag;
	}

	public void updateEvent(EventTO eventto) throws EventException {
		boolean flag1 = checkDate(eventto.getDate());

		if(!flag1) {
			eventerrormap.put(ErrorConstants.EVENT_DATE_ERROR, ErrorConstants.EVENT_DATE_ERROR_MESSAGE);
		}

		if(eventerrormap.size() > 0) {
			throw new EventException(eventerrormap);
		}
		else {
			eventdao.updateEvent(eventto);
		}
	}

	public void deleteEvent(EventTO eventto)  {
		eventdao.deleteEvent(eventto);
	}

	public void fetchEvent(EventTO eventto, StudentTO studentto) throws EventException {
		
		boolean eventflag = eventdao.fetchEvent(eventto, studentto);

		if(!eventflag) {
			eventerrormap.put(ErrorConstants.NO_EVENT_ERROR, ErrorConstants.NO_EVENT_ERROR_STUDENT_MESSAGE);
			throw new EventException(eventerrormap);
		}
	}
	
	public List<EventTO> fetchEvent() throws EventException {
		List<EventTO> eventlist = new ArrayList<EventTO>();
		eventlist = eventdao.fetchEvent();

		if(eventlist.size() == 0){
			throw new EventException(ErrorConstants.NO_EVENT_ERROR_MESSAGE);
		}
		return eventlist;
	}

	public List<EventTO> fetchEventBatch() {

		List<EventTO> batchlist = new ArrayList<EventTO>();
		batchlist = eventdao.fetchEventBatch();
		
		return batchlist;
	}
	
	public List<String> fetchBatch() {

		List<String> batchlist = new ArrayList<String>();
		batchlist = eventdao.fetchBatch();
		
		if(!batchlist.contains(KeywordConstants.DEFAULT_BATCH)) {
			batchlist.add(KeywordConstants.DEFAULT_BATCH);
		}
		
		return batchlist;
	}

	public void assignEvent(EventTO eventto) throws EventException {
		boolean flag = checkBatch(eventto);

		if(!flag) {
			eventerrormap.put(ErrorConstants.EVENT_DATE_BATCH_ERROR, ErrorConstants.EVENT_DATE_BATCH_ERROR_MESSAGE);
			throw new EventException(eventerrormap);
		}
		eventdao.assignEvent(eventto);
	}

	private boolean checkBatch(EventTO eventto) {
		boolean flag = true;

		List<String> datelist = eventdao.checkBatch(eventto);

		for(int i = 0; i<datelist.size()-1; i++) {
			String temp = datelist.get(i);
			for(int j = i+1; j<datelist.size();j++)
				if(temp.equals(datelist.get(j))) {
					flag = false;
					break;
				}
		}

		return flag;
	}
}
