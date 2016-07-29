package com.EventApp.Constants;

public class ErrorConstants {

	//Student Error
	public static final String USERNAME_STUDENT_EXIST_ERROR = "usernameerr";

	public static final String USERNAME_STUDENT_EXIST_ERROR_MESSAGE = "Username Already Exist";

	public static final String STUDENT_ASSIGNED_ERROR = "No Students Unassigned";
	
	public static final String NO_STUDENT_SELECTED_ERROR = "nostudent";

	public static final String NO_STUDENT_SELECTED_ERROR_MESSAGE = "Please Select a Student";
	//Admin Error
	public static final String SEARCH_USER_ERROR = "noMatchErr";

	public static final String SEARCH_USER_ERROR_MESSAGE = "No Match Found!!!";
	
	//Author Error
	public static final String USERNAME_AUTHOR_EXIST_ERROR = "usernameerr";

	public static final String USERNAME_AUTHOR_EXIST_ERROR_MESSAGE = "Username Already Exist";

	public static final String AUTHOR_UNAVAILABLE = "authorUnavailable";

	public static final String AUTHOR_UNAVAILABLE_ERROR_MESSAGE = "Currently no Author Available";

	//Event Error
	public static final String EVENT_DATE_ERROR = "eventdateerr";

	public static final String EVENT_DATE_ERROR_MESSAGE = "Check Event Date";

	public static final String EVENT_DATE_BATCH_ERROR = "eventbatcherr";

	public static final String EVENT_DATE_BATCH_ERROR_MESSAGE = "Event with same Date and batch exist";
	
	public static final String NO_EVENT_ERROR = "noevent";

	public static final String NO_EVENT_ERROR_MESSAGE = "Currently no event created";
	
	public static final String NO_EVENT_ERROR_STUDENT_MESSAGE = "You haven't registered for any events";
	
	public static final String NO_SESSION_ERROR = "nosession";

	public static final String NO_SESSION_ERROR_MESSAGE = "Currently no sessions";

	//Database Error
	public static final String DATABASE_UNKNOWN_ERROR = "databasunknowneerror";

	public static final String DATABASE_UNKNOWN_ERROR_MESSAGE = "Sorry Problem Occured!!! Pls try again later";
}
