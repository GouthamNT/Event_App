package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.SessionBO;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.SessionTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class DeleteSession
 */
@WebServlet("/DeleteSession")
public class DeleteSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteSession() {
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
		SessionTO sessionto = new SessionTO();
		SessionBO sessionbo = new SessionBO();
		UserSessionUtils usersessionutil = new UserSessionUtils();

		try {
			usersessionutil.checkSession(request.getSession(false));

			String session = request.getParameter("session");
			sessionto.setSession_id(session);
			sessionbo.deleteSession(sessionto);
		} catch (UserSessionException e) {
			response.setStatus(404);
		}
	}

}
