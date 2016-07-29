package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.EventBO;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.EventTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class DeleteEvent
 */
@WebServlet("/DeleteEvent")
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventTO eventto = new EventTO();
		EventBO eventbo = new EventBO();
		String event = request.getParameter("event");
		UserSessionUtils usersessionutil = new UserSessionUtils();
		try {
			usersessionutil.checkSession(request.getSession(false));
			eventto.setEvent_id(event);
			eventbo.deleteEvent(eventto);
		} catch (UserSessionException e) {
			response.setStatus(404);
		}
	}

}
