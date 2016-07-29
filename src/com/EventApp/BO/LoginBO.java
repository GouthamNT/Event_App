package com.EventApp.BO;

import java.util.HashMap;
import java.util.Map;

import com.EventApp.Constants.ErrorConstants;
import com.EventApp.DAO.ILogin;
import com.EventApp.DAO.LoginDAO;
import com.EventApp.Exception.StudentException;
import com.EventApp.TO.LoginTO;

public class LoginBO {
	ILogin logindao = new LoginDAO();
	Map<String,String> errormap = new HashMap<String,String>();
	
	public boolean validateCredentials(LoginTO loginto) {
		boolean loginflag;
		
		loginflag = logindao.fetchCredentials(loginto);
		return loginflag;
	}
	
	public void createCredentials(LoginTO loginto) throws StudentException {
		
		boolean checkusername = checkUserName(loginto.getUsername());
		if(!checkusername) {
			errormap.put(ErrorConstants.USERNAME_STUDENT_EXIST_ERROR, ErrorConstants.USERNAME_STUDENT_EXIST_ERROR_MESSAGE);
		} else {
			logindao.createCredentials(loginto);
		}

		if(errormap.size() > 0) {
			throw new StudentException(errormap);
		}
		
	}
	
	private boolean checkUserName(String username) {

		boolean checkusername = logindao.validateUsername(username);

		return checkusername;
	}
}
