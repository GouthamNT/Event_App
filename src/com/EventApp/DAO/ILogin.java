package com.EventApp.DAO;

import com.EventApp.TO.LoginTO;

public interface ILogin {
	boolean fetchCredentials(LoginTO loginto);
	void createCredentials(LoginTO loginto);
	public boolean validateUsername(String username);
}
