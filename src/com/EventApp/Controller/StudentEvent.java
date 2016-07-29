package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.EventApp.BO.EventBO;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.EventException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.EventTO;
import com.EventApp.TO.StudentTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class StudentEvent
 */
@WebServlet("/StudentEvent")
public class StudentEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventBO eventbo = new EventBO();
		EventTO eventto = new EventTO();
		StudentTO studentto = null;
		HttpSession session = request.getSession(false);
		RequestDispatcher dispatch = null;
		UserSessionUtils usersessionutil = new UserSessionUtils();
		try {
			usersessionutil.checkSession(request.getSession(false));
			studentto = (StudentTO) session.getAttribute("student");
			eventbo.fetchEvent(eventto, studentto);
			request.setAttribute("event", eventto);
			dispatch = request.getRequestDispatcher(ViewConstants.STUDENT_EVENT);
			dispatch.forward(request, response);
		} catch (EventException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.STUDENT_EVENT);
			request.setAttribute("error", e.getEventerrormap());
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
