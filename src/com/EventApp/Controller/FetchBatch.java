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

/**
 * Servlet implementation class FetchBatch
 */
@WebServlet("/FetchBatch")
public class FetchBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventBO eventbo = new EventBO();
		
		List<String> batchlist = eventbo.fetchBatch();

		JSONArray jsonarray = new JSONArray();
		JSONObject json = null;

		int length = batchlist.size();
		try {
			for(int i = 0; i < length; i++) {
				json = new JSONObject();

				json.put(DatabaseContants.EVENT_BATCH, batchlist.get(i));

				jsonarray.put(json);	
			}
			
			response.setContentType("application/json");
			response.getWriter().print(jsonarray);
		} catch (JSONException e) {
			response.setStatus(404);
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
