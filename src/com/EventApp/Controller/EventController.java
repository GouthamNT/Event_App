package com.EventApp.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.EventApp.BO.EventBO;
import com.EventApp.Constants.DatabaseContants;
import com.EventApp.Constants.KeywordConstants;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.EventException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.EventTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class EventController
 */
@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserSessionUtils usersessionutil = new UserSessionUtils();
	private EventTO eventto;
	private EventBO eventbo;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventTO eventto = null;
		EventBO eventbo = new EventBO();
		JSONArray jsonarray = new JSONArray();
		List<EventTO> eventlist = new ArrayList<EventTO>();
		try {
			
			usersessionutil.checkSession(request.getSession(false));
			eventlist = eventbo.fetchEvent();
			JSONObject json = null;
			int listlength = eventlist.size();
			for(int i = 0;i < listlength; i++) {
				eventto = eventlist.get(i);
				json = new JSONObject();

				json.put(DatabaseContants.EVENT_ID, eventto.getEvent_id());
				json.put(DatabaseContants.EVENT_NAME, eventto.getName());
				json.put(DatabaseContants.EVENT_DATE, eventto.getDate());
				json.put(DatabaseContants.EVENT_TIME, eventto.getTime());
				json.put(DatabaseContants.EVENT_ADDRESS, eventto.getAddress());
				json.put(DatabaseContants.EVENT_CITY, eventto.getCity());
				json.put(DatabaseContants.EVENT_PIN, eventto.getPin());
				json.put(DatabaseContants.EVENT_BATCH, eventto.getBatch_id());

				jsonarray.put(json);
			}
			System.out.println(jsonarray);
			System.out.println("json: " +json);
			response.setContentType("application/json");
			response.getWriter().print(jsonarray);

		} catch (EventException e) {
			response.setStatus(404);
			response.setContentType("application/text");
			response.getWriter().print(e.getMessage());
		} catch (JSONException e) {
			response.setStatus(404);
		} catch (UserSessionException e) {
			response.setStatus(404);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		eventto = new EventTO();
		eventbo = new EventBO();
		RequestDispatcher dispatch = null;

		String action = request.getParameter("action");
		String eventid = request.getParameter("eventid");
		String name = request.getParameter("eventname");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String pin = request.getParameter("pin");
		
		eventto.setEvent_id(eventid);
		eventto.setName(name);
		eventto.setDate(date);
		eventto.setTime(time);
		eventto.setAddress(address);
		eventto.setCity(city);
		eventto.setPin(Integer.parseInt(pin));
		
		if(action.equals("create")) {
			
			eventto.setBatch_id(KeywordConstants.DEFAULT_BATCH);

			try {
				usersessionutil.checkSession(request.getSession(false));
				eventbo.createEvent(eventto);
				dispatch = request.getRequestDispatcher(ViewConstants.ADMIN_INDEX);
				dispatch.forward(request, response);
			} catch (EventException e) {
				dispatch = request.getRequestDispatcher(ViewConstants.CREATE_EVENT);
				request.setAttribute("error", e.getEventerrormap());
				dispatch.forward(request, response);
			} catch (UserSessionException e) {
				dispatch = request.getRequestDispatcher(ViewConstants.ERROR);
				dispatch.forward(request, response);
			}
		}
	

	}

	protected EventTO getEventto() {
		return eventto;
	}

	protected EventBO getEventbo() {
		return eventbo;
	}
	
}
