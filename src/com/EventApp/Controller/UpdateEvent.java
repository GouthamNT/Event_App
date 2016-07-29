package com.EventApp.Controller;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.EventBO;
import com.EventApp.Exception.EventException;
import com.EventApp.TO.EventTO;

/**
 * Servlet implementation class UpdateEvent
 */
@WebServlet("/UpdateEvent")
public class UpdateEvent extends EventController implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see EventController#EventController()
     */
    public UpdateEvent() {
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
		super.doPost(request, response);
		
		EventTO eventto = getEventto();
		EventBO eventbo = getEventbo();
		
		try {
			eventbo.updateEvent(eventto);
		} catch (EventException e) {
			response.setStatus(404);
		}
		
	}

}
