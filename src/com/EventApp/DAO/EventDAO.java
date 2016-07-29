package com.EventApp.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.EventApp.Constants.QueryConstants;
import com.EventApp.TO.EventTO;
import com.EventApp.TO.StudentTO;
import com.EventApp.Util.DBUtil;

public class EventDAO implements IEvent {

	private Connection dbconnection = null;
	private PreparedStatement preparedstament = null;
	@Override
	public void createEvent(EventTO eventto) {

		try {
			dbconnection = DBUtil.getConnection();

			String eventid = generateEventId();
			eventto.setEvent_id(eventid);
			preparedstament = dbconnection.prepareStatement(QueryConstants.INSERT_EVENT);

			preparedstament.setString(1, eventto.getEvent_id());
			preparedstament.setString(2, eventto.getName());
			preparedstament.setString(3, eventto.getDate());
			preparedstament.setString(4, eventto.getTime());
			preparedstament.setString(5, eventto.getAddress());
			preparedstament.setString(6, eventto.getCity());
			preparedstament.setInt(7, eventto.getPin());
			preparedstament.setString(8, eventto.getBatch_id());

			preparedstament.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String generateEventId() throws SQLException {
		String lastid = "";
		String newid = "";

		preparedstament = dbconnection.prepareStatement(QueryConstants.FETCH_LAST_EVENT_ID);
		ResultSet rs = preparedstament.executeQuery();
		if(rs.next()) {
			lastid = rs.getString(1);
			if(lastid != null) {
				String[] idcomponents = lastid.split("-");
				String type = idcomponents[0];
				int numcomponent = Integer.parseInt(idcomponents[1]) + 1;
				NumberFormat formatter = new DecimalFormat("000");
				String series = formatter.format(numcomponent);
				newid = type + "-" + series;
			} else {
				newid = "EVT-001";
			}
		}

		return newid;
	}

	@Override
	public void assignEvent(EventTO eventto) {
		try {
			dbconnection = DBUtil.getConnection();
			preparedstament = dbconnection.prepareStatement(QueryConstants.UPDATE_EVENT_BATCH);

			preparedstament.setString(1, eventto.getBatch_id());
			preparedstament.setString(2, eventto.getEvent_id());

			preparedstament.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<EventTO> fetchEvent() {
		List<EventTO> events = new ArrayList<EventTO>();

		EventTO eventto = null;

		String eventid = "";
		String eventname = "";
		String date = "";
		String time = "";
		String address = "";
		String city = "";
		String pin = "";
		String batch = "";

		try {
			dbconnection = DBUtil.getConnection();
			preparedstament = dbconnection.prepareStatement(QueryConstants.FETCH_EVENTS);
			ResultSet rs = preparedstament.executeQuery();

			while(rs.next()) {
				eventto = new EventTO();

				eventid = rs.getString(1);
				eventname = rs.getString(2);
				date = rs.getString(3);
				time = rs.getString(4);
				address = rs.getString(5);
				city = rs.getString(6);
				pin = rs.getString(7);
				batch = rs.getString(8);

				eventto.setEvent_id(eventid);
				eventto.setName(eventname);
				eventto.setDate(date);
				eventto.setTime(time);
				eventto.setAddress(address);
				eventto.setCity(city);
				eventto.setPin(Integer.parseInt(pin));
				eventto.setBatch_id(batch);

				events.add(eventto);
			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return events;
	}

	@Override
	public void updateEvent(EventTO eventto) {
		try {
			dbconnection = DBUtil.getConnection();

			preparedstament = dbconnection.prepareStatement(QueryConstants.UPDATE_EVENT);

			preparedstament.setString(1, eventto.getName());
			preparedstament.setString(2, eventto.getDate());
			preparedstament.setString(3, eventto.getTime());
			preparedstament.setString(4, eventto.getAddress());
			preparedstament.setString(5, eventto.getCity());
			preparedstament.setInt(6, eventto.getPin());
			preparedstament.setString(7, eventto.getEvent_id());

			int a = preparedstament.executeUpdate();
			System.out.println(a);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEvent(EventTO eventto) {
		try {
			dbconnection = DBUtil.getConnection();
			preparedstament = dbconnection.prepareStatement(QueryConstants.DELETE_EVENT);
			preparedstament.setString(1, eventto.getEvent_id());
			preparedstament.execute();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<String> checkBatch(EventTO eventto) {
		List<String> eventdates = new ArrayList<String>();

		try {
			dbconnection = DBUtil.getConnection();
			preparedstament = dbconnection.prepareStatement(QueryConstants.FETCH_EVENTDATE);
			preparedstament.setString(1, eventto.getBatch_id());
			ResultSet rs = preparedstament.executeQuery();

			while(rs.next()) {

				eventdates.add(rs.getString(1));
			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return eventdates;
	}

	@Override
	public List<EventTO> fetchEventBatch() {
		List<EventTO> batchlist = new ArrayList<EventTO>();

		EventTO eventto = null;

		String eventid = "";
		String eventname = "";
		String batch = "";

		try {
			dbconnection = DBUtil.getConnection();
			preparedstament = dbconnection.prepareStatement(QueryConstants.FETCH_EVENT_BATCH);
			ResultSet rs = preparedstament.executeQuery();
			while(rs.next()) {
				eventto = new EventTO();

				eventid = rs.getString(1);
				eventname = rs.getString(2);
				batch = rs.getString(3);

				eventto.setEvent_id(eventid);
				eventto.setName(eventname);
				eventto.setBatch_id(batch);

				batchlist.add(eventto);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return batchlist;
	}

	public List<String> fetchBatch() {
		List<String> batchlist = new ArrayList<String>();

		try {
			dbconnection = DBUtil.getConnection();
			preparedstament = dbconnection.prepareStatement(QueryConstants.FETCH_BATCH);
			ResultSet rs = preparedstament.executeQuery();
			while(rs.next()) {
				batchlist.add(rs.getString(1));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return batchlist;
	}

	@Override
	public boolean fetchEvent(EventTO eventto, StudentTO studentto) {
		boolean flag = false;
		try {
			dbconnection = DBUtil.getConnection();
			preparedstament = dbconnection.prepareStatement(QueryConstants.FETCH_STUDENT_EVENT);
			preparedstament.setString(1, studentto.getStudent_id());
			ResultSet rs = preparedstament.executeQuery();
			while(rs.next()) {

				eventto.setEvent_id(rs.getString(1));
				eventto.setName(rs.getString(2));
				String date = formatDate(rs.getString(3));
				eventto.setDate(date);
				eventto.setTime(rs.getString(4));
				eventto.setAddress(rs.getString(5));
				eventto.setCity(rs.getString(6));
				eventto.setPin(rs.getInt(7));
				eventto.setBatch_id(rs.getString(8));
				studentto.setBatch_id(rs.getString(8));
				
				flag = true;
				
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}
	
	private String formatDate(String date) {
		SimpleDateFormat currentformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat readableformat = new SimpleDateFormat("dd-MM-yyyy");
		String newdate = null;
		try {
			Date unformatteddate = currentformat.parse(date);
			newdate = readableformat.format(unformatteddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newdate;
	}

}
