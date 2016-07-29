package com.EventApp.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.AdminBO;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.UserException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class SearchUser
 */
@WebServlet("/SearchUser")
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("searchKeyword");
		AdminBO adminbo = new AdminBO();
		RequestDispatcher dispatch = null;
		UserSessionUtils usersessionutil = new UserSessionUtils();
		try {
			
			usersessionutil.checkSession(request.getSession(false));
			List<String> username = adminbo.fetchUserName(keyword);
			
			request.setAttribute("username", username);
			request.setAttribute("result", 1);
			dispatch = request.getRequestDispatcher(ViewConstants.DELETE_USER);
			dispatch.forward(request, response);
			
		} catch (UserException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.DELETE_USER);
			request.setAttribute("error", e.getUserErrormap());
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

	}

}
