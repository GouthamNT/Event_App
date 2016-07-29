package com.EventApp.Util;

import javax.servlet.http.HttpSession;

import com.EventApp.Exception.UserSessionException;

public class UserSessionUtils {

	public void checkSession(HttpSession session) throws UserSessionException {
		String usertype = (String)session.getAttribute("usertype");
		
		if(usertype == null) {
			throw new UserSessionException();
		}
	}
}
