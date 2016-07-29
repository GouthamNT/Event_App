package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.EventApp.BO.LoginBO;
import com.EventApp.BO.StudentBO;
import com.EventApp.Constants.KeywordConstants;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.TO.LoginTO;
import com.EventApp.TO.StudentTO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginTO loginto = new LoginTO();
		LoginBO loginbo = new LoginBO();
		boolean loginflag;

		String username = request.getParameter("username");
		String password = request.getParameter("password");


		loginto.setUsername(username);
		loginto.setPassword(password);
		loginflag = loginbo.validateCredentials(loginto);
		String usertype = loginto.getUsertype();
		if(loginflag) {
			HttpSession session = request.getSession();
			RequestDispatcher dispatcher = null;
			if(usertype.equals(KeywordConstants.USER_TYPE_STUDENT)) {
				StudentTO studentto = new StudentTO();
				StudentBO studentbo = new StudentBO();

				studentto.setUsername(loginto.getUsername());
				//studentto.setUsername("Test");

				studentbo.getStudentID(studentto);

				session.setAttribute("student", studentto);
				session.setAttribute("usertype", KeywordConstants.USER_TYPE_STUDENT);
				dispatcher = request.getRequestDispatcher(ViewConstants.USER_INDEX);
				dispatcher.forward(request, response);
			} else {
				session.setAttribute("usertype", KeywordConstants.USER_TYPE_ADMIN);
				dispatcher = request.getRequestDispatcher(ViewConstants.ADMIN_INDEX);
				dispatcher.forward(request, response);
			}
		} 
		else {

		}
	}

}
