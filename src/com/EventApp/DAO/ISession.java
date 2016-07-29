package com.EventApp.DAO;

import java.util.List;

import com.EventApp.TO.SessionTO;

public interface ISession {
	public void createSession(SessionTO sessionto);
	public void updateSession(SessionTO sessionto);
	public void deleteSession(SessionTO sessionto);
	public List<SessionTO> fecthSession(SessionTO sessionto);
	
}
