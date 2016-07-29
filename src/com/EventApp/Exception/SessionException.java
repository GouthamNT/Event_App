package com.EventApp.Exception;

import java.util.HashMap;
import java.util.Map;

public class SessionException extends Exception {

	private static final long serialVersionUID = 1L;

	private Map<String,String> sessionerrormap = new HashMap<String,String>();

	public SessionException(Map<String,String> sessionerrormap) {
		this.sessionerrormap = sessionerrormap;
	}

	public Map<String, String> getSessionerrormap() {
		return sessionerrormap;
	}

	public void setSessionerrormap(Map<String, String> sessionerrormap) {
		this.sessionerrormap = sessionerrormap;
	}

	
}
