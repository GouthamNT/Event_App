package com.EventApp.Constants;

public class QueryConstants {
	
	public static final String CHECK_CREDENTIALS = "select * from LOGIN_CREDENTIALS where USERNAME = ? and PASSWORD = ?";
	
	public static final String INSERT_CREDENTIALS = "insert into LOGIN_CREDENTIALS (USERNAME, PASSWORD, USER_TYPE) values (?,?,?)";
	
	public static final String FETCH_USERNAME = "select USERNAME from LOGIN_CREDENTIALS where USERNAME = ?";
	
	public static final String INSERT_STUDENT = "insert into STUDENT_DETAILS (STUDENT_ID, USERNAME, NAME, AGE, EMAIL_ID, ADDRESS, BATCH_ID) values (?,?,?,?,?,?,?)";
	
	public static final String FETCH_STUDENT_ID = "select STUDENT_ID from STUDENT_DETAILS where USERNAME = ?";
	
	public static final String FETCH_STUDENT = "select NAME, AGE, EMAIL_ID, ADDRESS from STUDENT_DETAILS where STUDENT_ID = ?";
	
	public static final String UPDATE_STUDENT = "update STUDENT_DETAILS EMAIL_ID = ?, ADDRESS = ? where STUDENT_ID = ?";
	
	public static final String FETCH_LAST_STUDENT_ID = "select max(student_id) from STUDENT_DETAILS";
	
	public static final String FETCH_STUDENTNAME = "select NAME,USERNAME from STUDENT_DETAILS where BATCH_ID='unassigned'";
	
	public static final String FETCH_ALL_USERNAME = "select username from LOGIN_CREDENTIALS where USERNAME like ?";
	
	public static final String DELETE_USERNAME = "delete from LOGIN_CREDENTIALS where USERNAME = ?";
	
	public static final String INSERT_AUTHOR = "insert into AUTHOR_DETAILS (AUTHOR_ID, NAME, AGE, EXPERIENCE, EMAIL_ID, ADDRESS, RATING) values (?,?,?,?,?,?,?)";
	
	public static final String UPDATE_AUTHOR = "update AUTHOR_DETAILS set NAME = ?, AGE = ?, EXPERIENCE = ?, EMAIL_ID = ?, ADDRESS = ? where AUTHOR_ID = ?";
	
	public static final String FETCH_AUTHOR_UNASSIGNED = "select AUTHOR_ID, NAME, AGE, EXPERIENCE,	EMAIL_ID, ADDRESS, RATING from AUTHOR_DETAILS where AUTHOR_ID not in (select AUTHOR_ID from SESSION_DETAILS)";
	
	public static final String FETCH_AUTHOR = "select AUTHOR_ID, NAME, AGE, EXPERIENCE,	EMAIL_ID, ADDRESS, RATING from AUTHOR_DETAILS";
	
	public static final String FETCH_AUTHOR_SINGLE = "select AUTHOR_ID, NAME, AGE, EXPERIENCE,	EMAIL_ID, ADDRESS, RATING from AUTHOR_DETAILS where AUTHOR_ID = ?";
	
	public static final String FETCH_AUTHOR_NAME = "select NAME from AUTHOR_DETAILS where AUTHOR_ID = ?";
	
	public static final String FETCH_LAST_AUTHOR_ID = "select max(author_id) from AUTHOR_DETAILS";
	
	public static final String INSERT_EVENT = "insert into EVENT_DETAILS (EVENT_ID, EVENT_NAME, EVENT_DATE, TIME, ADDRESS, CITY , PIN, BATCH_ID) values (?,?,?,?,?,?,?,?)";
	
	public static final String UPDATE_EVENT = "update EVENT_DETAILS set EVENT_NAME = ?, EVENT_DATE  = ?, TIME  = ?, ADDRESS  = ?, CITY = ?, PIN  = ? where EVENT_ID = ?";
	
	public static final String FETCH_LAST_EVENT_ID = "select max(event_id) from EVENT_DETAILS";
	
	public static final String FETCH_EVENTS = "select EVENT_ID, EVENT_NAME, EVENT_DATE, TIME, ADDRESS, CITY, PIN, BATCH_ID from EVENT_DETAILS";
	
	public static final String FETCH_STUDENT_EVENT = "select EVENT_ID, EVENT_NAME, EVENT_DATE, TIME, ADDRESS, CITY, PIN, BATCH_ID from EVENT_DETAILS where BATCH_ID in (select BATCH_ID from STUDENT_DETAILS where STUDENT_ID = ?)";
	
	public static final String FETCH_EVENT_BATCH = "select EVENT_ID, EVENT_NAME, BATCH_ID from EVENT_DETAILS";
	
	public static final String DELETE_EVENT = "delete from EVENT_DETAILS where EVENT_ID = ?";
	
	public static final String FETCH_BATCH = "select distinct BATCH_ID from STUDENT_DETAILS";
	
	public static final String FETCH_LAST_SESSION_ID = "select max(session_id) from SESSION_DETAILS where EVENT_ID = ?";
	
	public static final String INSERT_SESSION = "insert into SESSION_DETAILS (SESSION_ID, TITLE, DURATION, DESCRIPTION, EVENT_ID, AUTHOR_ID) values (?,?,?,?,?,?)";
	
	public static final String UPDATE_SESSION = "update SESSION_DETAILS set TITLE = ?, DURATION = ?, DESCRIPTION = ?, AUTHOR_ID = ? where SESSION_ID = ?";
	
	public static final String FETCH_SESSION = "select SESSION_DETAILS.SESSION_ID, TITLE, DURATION, DESCRIPTION, AUTHOR_DETAILS.NAME, SESSION_RATING.RATING, AUTHOR_DETAILS.author_id from SESSION_DETAILS left join AUTHOR_DETAILS on AUTHOR_DETAILS.author_id = SESSION_DETAILS.author_id left join SESSION_RATING on SESSION_DETAILS.session_id = SESSION_RATING.session_id where SESSION_DETAILS.EVENT_ID = ?";
	
	public static final String DELETE_SESSION = "delete from SESSION_DETAILS where SESSION_ID = ?";
	
	public static final String FETCH_EVENTDATE = "select EVENT_DATE from EVENT_DETAILS where BATCH_ID = ?";
	
	public static final String FETCH_LAST_BATCH_ID = "select max(batch_id) from STUDENT_DETAILS where BATCH_ID like 'BAT-%'";
	
	public static final String UPDATE_STUDENT_BATCH = "update STUDENT_DETAILS set BATCH_ID = ? where USERNAME = ?";
	
	public static final String UPDATE_EVENT_BATCH = "update EVENT_DETAILS set BATCH_ID = ? where EVENT_ID = ?";
	
	public static final String INSERT_RATING = "insert into SESSION_RATING (SESSION_ID, STUDENT_USERNAME, RATING) values (?,?,?)";
}
