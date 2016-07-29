package com.EventApp.Exception;

import java.util.HashMap;
import java.util.Map;

public class EventException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	private Map<String,String> eventerrormap = new HashMap<String,String>();

	public EventException(Map<String,String> studenterrormap) {
		this.eventerrormap = studenterrormap;
	}
	
	public EventException(String message) {
		this.message = message;
	}

	public Map<String, String> getEventerrormap() {
		return eventerrormap;
	}

	public void setEventerrormap(Map<String, String> eventerrormap) {
		this.eventerrormap = eventerrormap;
	}
	
	public String getMessage() {
		return message;
	}
	
}
