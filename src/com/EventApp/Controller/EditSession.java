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
 * Servlet implementation class EditSession
 */
@WebServlet("/EditSession")
public class EditSession extends SessionController {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditSession() {
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
		super.doPost(request, response);
		SessionTO sessionto = getSessionto();
		SessionBO sessionbo = getSessionbo();
		UserSessionUtils usersessionutil = new UserSessionUtils();

		try {
			usersessionutil.checkSession(request.getSession(false));
			String session = request.getParameter("session");

			sessionto.setSession_id(session);
			sessionbo.updateSession(sessionto);
		} catch (UserSessionException e) {
			response.setStatus(404);
		}
	}

}
