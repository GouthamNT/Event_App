package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.EventApp.BO.StudentBO;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.StudentTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class EditStudent
 */
@WebServlet("/EditStudent")
public class EditStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentTO studentto = null;
		StudentBO studentbo = new StudentBO();
		RequestDispatcher dispatch = null;
		UserSessionUtils usersessionutil = new UserSessionUtils();

		try {
			usersessionutil.checkSession(request.getSession(false));

			HttpSession studentses = request.getSession(false);
			studentto = (StudentTO) studentses.getAttribute("student");
			studentbo.viewStudent(studentto);

			request.setAttribute("student", studentto);
			dispatch = request.getRequestDispatcher(ViewConstants.EDIT_PROFILE);
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
