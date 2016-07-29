package com.EventApp.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.EventBO;
import com.EventApp.Constants.ErrorConstants;
import com.EventApp.Exception.EventException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.EventTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class AssignEvent
 */
@WebServlet("/AssignEvent")
public class AssignEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserSessionUtils usersessionutil = new UserSessionUtils();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignEvent() {
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
		EventTO eventto = new EventTO();
		EventBO eventbo = new EventBO();
		
		String batchid = request.getParameter("batchid");
		String eventid = request.getParameter("eventid");

		eventto.setBatch_id(batchid);
		eventto.setEvent_id(eventid);

		try {
			usersessionutil.checkSession(request.getSession(false));
			eventbo.assignEvent(eventto);
		} catch (EventException e) {
			Map<String,String> error = e.getEventerrormap();
			String errormsg = error.get(ErrorConstants.EVENT_DATE_BATCH_ERROR);

			e.printStackTrace();
			response.setContentType("application/text");
			response.getWriter().print(errormsg);
		} catch (UserSessionException e) {
			response.setStatus(404);
		}
		
	}

}
