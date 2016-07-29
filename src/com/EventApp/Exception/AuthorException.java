package com.EventApp.Exception;

import java.util.HashMap;
import java.util.Map;

public class AuthorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,String> authorerrormap = new HashMap<String,String>();
	private String message;

	public AuthorException(Map<String,String> authorerrormap) {
		this.authorerrormap = authorerrormap;
	}
	
	public AuthorException(String message) {
		this.message = message;
	}
	
	public Map<String,String> getAuthorerrormap() {
		return authorerrormap;
	}

	public void setAuthorerrormap(Map<String,String> studenterrormap) {
		this.authorerrormap = studenterrormap;
	}

	public String getMessage() {
		return message;
	}
	
	
}
