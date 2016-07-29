package com.EventApp.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.AuthorBO;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.AuthorException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.AuthorTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class AddSession
 */
@WebServlet("/AddSession")
public class AddSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserSessionUtils usersessionutil = new UserSessionUtils();
		RequestDispatcher dispatch = null;
		try {

			usersessionutil.checkSession(request.getSession());
			String event = request.getParameter("event");
			request.setAttribute("event", event);

			AuthorBO authorbo = new AuthorBO();
			List<AuthorTO> authorOptions = authorbo.fetchAuthor();
			request.setAttribute("authorOptions", authorOptions);

			dispatch = request.getRequestDispatcher(ViewConstants.CREATE_SESSION);
			dispatch.forward(request, response);
		} catch (UserSessionException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.ERROR);
			dispatch.forward(request, response);
		} catch (AuthorException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.CREATE_SESSION);
			request.setAttribute("error", e.getMessage());
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
