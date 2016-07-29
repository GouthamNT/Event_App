package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.AdminBO;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUser() {
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
		String[] username = request.getParameterValues("username");
		UserSessionUtils usersessionutil = new UserSessionUtils();
		RequestDispatcher dispatch = null;
		
		try {
			usersessionutil.checkSession(request.getSession(false));
			AdminBO adminbo = new AdminBO();
			adminbo.deleteUser(username);
			dispatch = request.getRequestDispatcher(ViewConstants.ADMIN_INDEX);
			dispatch.forward(request, response);
		} catch (UserSessionException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.ERROR);
			dispatch.forward(request, response);
		}

	}

}
