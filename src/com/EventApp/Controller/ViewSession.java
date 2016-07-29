package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class ViewSession
 */
@WebServlet("/ViewSession")
public class ViewSession extends SessionController {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		RequestDispatcher dispatch = null;
		UserSessionUtils usersessionutil = new UserSessionUtils();

		try {
			usersessionutil.checkSession(request.getSession(false));

			dispatch = request.getRequestDispatcher(ViewConstants.ADMIN_SESSION);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
