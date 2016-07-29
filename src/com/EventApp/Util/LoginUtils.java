package com.EventApp.Util;

import com.EventApp.BO.LoginBO;
import com.EventApp.Exception.StudentException;
import com.EventApp.TO.LoginTO;

public class LoginUtils {

	public void createLogin(String username, String password, String usertype) throws StudentException {
		 
		LoginTO loginto = new LoginTO();
		LoginBO loginbo = new LoginBO();

		loginto.setUsername(username);
		loginto.setPassword(password);
		loginto.setUsertype(usertype);

		loginbo.createCredentials(loginto);
	}
}
