package com.EventApp.Exception;

import java.util.HashMap;
import java.util.Map;

import com.EventApp.Constants.ErrorConstants;

public class DatabaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String,String> databaseerrormap = new HashMap<String,String>();

	DatabaseException() {
		databaseerrormap.put(ErrorConstants.DATABASE_UNKNOWN_ERROR, ErrorConstants.DATABASE_UNKNOWN_ERROR_MESSAGE);
	}

	DatabaseException(Map<String,String> databaseerrormap) {
		this.databaseerrormap = databaseerrormap;
	}

	public Map<String, String> getDatabaseerrormap() {
		return databaseerrormap;
	}

	public void setDatabaseerrormap(Map<String, String> databaseerrormap) {
		this.databaseerrormap = databaseerrormap;
	}

}
