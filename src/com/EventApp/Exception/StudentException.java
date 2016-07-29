package com.EventApp.Exception;

import java.util.HashMap;
import java.util.Map;

public class StudentException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,String> studenterrormap = new HashMap<String,String>();
	private String message;

	public StudentException(Map<String,String> studenterrormap) {
		this.studenterrormap = studenterrormap;
	}
	
	public StudentException(String message) {
		this.message = message;
	}
	
	public Map<String,String> getStudenterrormap() {
		return studenterrormap;
	}

	public void setStudenterrormap(Map<String,String> studenterrormap) {
		this.studenterrormap = studenterrormap;
	}

	public String getMessage() {
		return message;
	}
	
	
}
