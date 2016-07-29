package com.EventApp.Exception;

import java.util.HashMap;
import java.util.Map;

public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String,String> userErrormap = new HashMap<String,String>();

	public UserException(Map<String,String> userErrormap) {
		this.userErrormap = userErrormap;
	}

	public Map<String, String> getUserErrormap() {
		return userErrormap;
	}

	public void setUserErrormap(Map<String, String> userErrormap) {
		this.userErrormap = userErrormap;
	}
	
}
