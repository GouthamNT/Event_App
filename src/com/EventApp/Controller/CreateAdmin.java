package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.Constants.KeywordConstants;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.StudentException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.Util.LoginUtils;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class CreateAdmin
 */
@WebServlet("/CreateAdmin")
public class CreateAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAdmin() {
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
		
		String username = "";
		String password = "";
		
		username = request.getParameter("username");
		password = request.getParameter("password");
		
		LoginUtils login = new LoginUtils();
		RequestDispatcher dispatch = null;
		UserSessionUtils usersessionutil = new UserSessionUtils();
		
		try {
			usersessionutil.checkSession(request.getSession(false));
			login.createLogin(username, password, KeywordConstants.USER_TYPE_ADMIN);
			dispatch = request.getRequestDispatcher(ViewConstants.ADMIN_INDEX);
			dispatch.forward(request, response);
			
		} catch (StudentException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.CREATE_AUTHOR);
			request.setAttribute("error", e.getStudenterrormap());
			dispatch.forward(request, response);
		} catch (UserSessionException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.ERROR);
			dispatch.forward(request, response);
		}
		
	}

}
