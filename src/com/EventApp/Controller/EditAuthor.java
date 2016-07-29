package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.AuthorBO;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.AuthorTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class EditAuthor
 */
@WebServlet("/EditAuthor")
public class EditAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditAuthor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorTO authorto = new AuthorTO();
		AuthorBO authorbo = new AuthorBO();
		RequestDispatcher dispatch = null;
		UserSessionUtils usersessionutil = new UserSessionUtils();

		try {
			usersessionutil.checkSession(request.getSession(false));
			String authorid = request.getParameter("authorid");

			authorto.setAuthor_id(authorid);
			authorbo.editAuthor(authorto);
			System.out.println(authorto);
			request.setAttribute("author", authorto);
			dispatch = request.getRequestDispatcher(ViewConstants.EDIT_AUTHOR);
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
