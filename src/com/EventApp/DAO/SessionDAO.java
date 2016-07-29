package com.EventApp.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.EventApp.Constants.QueryConstants;
import com.EventApp.TO.SessionTO;
import com.EventApp.Util.DBUtil;

public class SessionDAO implements ISession{
	private Connection dbconnection = null;
	private PreparedStatement preparedstament = null;

	@Override
	public void createSession(SessionTO sessionto) {

		try {
			dbconnection = DBUtil.getConnection();
			String sessionid = generateSessionId(sessionto.getEvent_id());
			preparedstament = dbconnection.prepareStatement(QueryConstants.INSERT_SESSION);

			preparedstament.setString(1, sessionid);
			preparedstament.setString(2, sessionto.getTitle());
			preparedstament.setString(3, sessionto.getDuration());
			preparedstament.setString(4, sessionto.getDescription());
			preparedstament.setString(5, sessionto.getEvent_id());
			preparedstament.setString(6, sessionto.getAuthor_id());

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

	private String generateSessionId(String eventid) throws SQLException {
		String lastid = "";
		String newid = "";

		preparedstament = dbconnection.prepareStatement(QueryConstants.FETCH_LAST_SESSION_ID);
		preparedstament.setString(1, eventid);
		ResultSet rs = preparedstament.executeQuery();
		if(rs.next()) {
			lastid = rs.getString(1);
			System.out.println(lastid);
			if(lastid != null) {
				String[] idcomponents = lastid.split("-");
				eventid = idcomponents[0] + "-" + idcomponents[1];
				String type = idcomponents[2];
				int numcomponent = Integer.parseInt(idcomponents[3]) + 1;
				NumberFormat formatter = new DecimalFormat("000");
				String series = formatter.format(numcomponent);
				newid = eventid + "-" + type + "-" + series;
			} else {
				newid = eventid + "-" + "SES-001";
			}
		}

		return newid;
	}

	@Override
	public void updateSession(SessionTO sessionto) {

		try {
			dbconnection = DBUtil.getConnection();
			//String sessionid = generateSessionId(sessionto.getEvent_id());
			preparedstament = dbconnection.prepareStatement(QueryConstants.UPDATE_SESSION);

			preparedstament.setString(1, sessionto.getTitle());
			preparedstament.setString(2, sessionto.getDuration());
			preparedstament.setString(3, sessionto.getDescription());
			preparedstament.setString(4, sessionto.getAuthor_id());
			preparedstament.setString(5, sessionto.getSession_id());

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
	public void deleteSession(SessionTO sessionto) {
		try {
			dbconnection = DBUtil.getConnection();
			//String sessionid = generateSessionId(sessionto.getEvent_id());
			preparedstament = dbconnection.prepareStatement(QueryConstants.DELETE_SESSION);
			preparedstament.setString(1, sessionto.getSession_id());
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
	public List<SessionTO> fecthSession(SessionTO sessionto) {
		
		List<SessionTO> sessionlist = new ArrayList<SessionTO>();
		SessionTO session = null;
		try {
			dbconnection = DBUtil.getConnection();
			//String sessionid = generateSessionId(sessionto.getEvent_id());
			preparedstament = dbconnection.prepareStatement(QueryConstants.FETCH_SESSION);
			preparedstament.setString(1, sessionto.getEvent_id());
			
			ResultSet rs = preparedstament.executeQuery();
			
			while(rs.next()) {
				session = new SessionTO();
				
				session.setSession_id(rs.getString(1));
				session.setTitle(rs.getString(2));
				session.setDuration(rs.getString(3));
				session.setDescription(rs.getString(4));
				session.setAuthor(rs.getString(5));
				session.setRating(rs.getInt(6));
				session.setAuthor_id(rs.getString(7));
				session.setEvent_id(sessionto.getEvent_id());;
				
				sessionlist.add(session);
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
		return sessionlist;
	}

}
