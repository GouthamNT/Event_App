package com.EventApp.BO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.EventApp.Constants.ErrorConstants;
import com.EventApp.DAO.ISession;
import com.EventApp.DAO.SessionDAO;
import com.EventApp.Exception.SessionException;
import com.EventApp.TO.SessionTO;

public class SessionBO {
	ISession sessiondao = new SessionDAO();
	Map<String,String> sessionerrormap = new HashMap<String,String>();

	public void createSession(SessionTO sessionto) {
		sessiondao.createSession(sessionto);
	}
	
	public void updateSession(SessionTO sessionto) {
		sessiondao.updateSession(sessionto);
	}

	public void deleteSession(SessionTO sessionto) {
		sessiondao.deleteSession(sessionto);
	}

	public List<SessionTO> fetchSession(SessionTO sessionto) throws SessionException {
		List<SessionTO> sessionlist = sessiondao.fecthSession(sessionto);
		
		if(sessionlist.size() == 0){
			sessionerrormap.put(ErrorConstants.NO_EVENT_ERROR, ErrorConstants.NO_EVENT_ERROR_MESSAGE);
			throw new SessionException(sessionerrormap);
		}
		return sessionlist;
	}
}
