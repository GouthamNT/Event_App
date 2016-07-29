package com.EventApp.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.EventApp.BO.SessionBO;
import com.EventApp.Constants.KeywordConstants;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.SessionException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.SessionTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class SessionController
 */
@WebServlet("/SessionController")
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionTO sessionto = new SessionTO();
	private SessionBO sessionbo = new SessionBO();
	UserSessionUtils usersessionutil = new UserSessionUtils();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SessionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sessionto = new SessionTO();
		sessionbo = new SessionBO();

		HttpSession usersession = request.getSession(false);

		String usertype = (String) usersession.getAttribute("usertype");
		usertype = "admin";
		System.out.println(request.getParameter("event"));
		String eventid = request.getParameter("event");
		String eventname = request.getParameter("eventname");

		sessionto.setEvent_id(eventid);
		RequestDispatcher dispatch = null;
		request.setAttribute("eventid", eventid);
		request.setAttribute("eventname", eventname);
		List<SessionTO> sessionlist = new ArrayList<SessionTO>();
		try {
			usersessionutil.checkSession(usersession);
			sessionlist = sessionbo.fetchSession(sessionto);
			request.setAttribute("session", sessionlist);

			if(usertype.equals(KeywordConstants.USER_TYPE_STUDENT)){
				dispatch = request.getRequestDispatcher(ViewConstants.STUDENT_SESSION);
				dispatch.forward(request, response);
			}

		} catch (SessionException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.STUDENT_SESSION);
			request.setAttribute("error", e.getSessionerrormap());
			dispatch.forward(request, response);
		} catch (UserSessionException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.ERROR);
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sessionto = new SessionTO();
		sessionbo = new SessionBO();
		try {
			usersessionutil.checkSession(request.getSession(false));

			String action = request.getParameter("action");

			String title = request.getParameter("title");
			String authorid = request.getParameter("authorid");
			String duration = request.getParameter("duration");
			String description = request.getParameter("description");
			String eventid = request.getParameter("event");

			sessionto.setTitle(title);
			sessionto.setAuthor_id(authorid);
			sessionto.setDuration(duration);
			sessionto.setDescription(description);
			sessionto.setEvent_id(eventid);

			setSessionto(sessionto);
			setSessionbo(sessionbo);

			if(action.equals("create")) {
				sessionbo.createSession(sessionto);
			}
		} catch (UserSessionException e) {
			RequestDispatcher dispatch = request.getRequestDispatcher(ViewConstants.ERROR);
			dispatch.forward(request, response);
		}

	}

	protected SessionTO getSessionto() {
		return sessionto;
	}

	protected void setSessionto(SessionTO sessionto) {
		this.sessionto = sessionto;
	}

	protected SessionBO getSessionbo() {
		return sessionbo;
	}

	protected void setSessionbo(SessionBO sessionbo) {
		this.sessionbo = sessionbo;
	}

}
