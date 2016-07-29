package com.EventApp.BO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.EventApp.Constants.ErrorConstants;
import com.EventApp.DAO.AdminDAO;
import com.EventApp.DAO.IAdmin;
import com.EventApp.Exception.UserException;

public class AdminBO {
	
	IAdmin admindao = new AdminDAO();
	Map<String,String> error = new HashMap<String,String>();
	
	public List<String> fetchUserName(String keyword) throws UserException {
		List<String> usernames = admindao.fetchUsernames(keyword);
		
		if(!(usernames.size() > 0)) {
			error.put(ErrorConstants.SEARCH_USER_ERROR, ErrorConstants.SEARCH_USER_ERROR_MESSAGE);
		}
		
		if(error.size() > 0) {
			throw new UserException(error);
		}
		
		return usernames;
	}
	
	public void deleteUser(String[] username) {
		admindao.deleteUser(username);
	}

}
