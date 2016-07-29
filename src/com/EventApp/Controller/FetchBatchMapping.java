package com.EventApp.Controller;

import java.io.IOException;
import java.util.List;

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
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.EventTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class FetchBatchMapping
 */
@WebServlet("/FetchBatchMapping")
public class FetchBatchMapping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBatchMapping() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventTO eventto = null;
		EventBO eventbo = new EventBO();
		
		List<EventTO> batchlist = eventbo.fetchEventBatch();

		JSONArray jsonarray = new JSONArray();
		JSONObject json = null;

		int length = batchlist.size();UserSessionUtils usersessionutil = new UserSessionUtils();

		try {
			usersessionutil.checkSession(request.getSession(false));
			for(int i = 0; i < length; i++) {
				json = new JSONObject();
				eventto = batchlist.get(i);
				if(eventto.getName() != null){
					json.put(DatabaseContants.EVENT_NAME, eventto.getName());
					json.put(DatabaseContants.EVENT_ID, eventto.getEvent_id());
					json.put(DatabaseContants.EVENT_BATCH, eventto.getBatch_id());

					jsonarray.put(json);	
				}
			}
			response.setContentType("application/json");
			response.getWriter().print(jsonarray);
		} catch (JSONException e) {
			response.setStatus(404);
		} catch (UserSessionException e) {
			response.setStatus(404);
			/*RequestDispatcher dispatch = request.getRequestDispatcher(ViewConstants.ERROR);
			dispatch.forward(request, response);*/
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
