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

import com.EventApp.BO.AuthorBO;
import com.EventApp.Exception.AuthorException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.AuthorTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class AuthorOptions
 */
@WebServlet("/AuthorOptions")
public class AuthorOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserSessionUtils usersessionutil = new UserSessionUtils();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorOptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AuthorBO authorbo = new AuthorBO();
		AuthorTO authorto = null;

		JSONObject json = null;
		JSONArray jsonarray = new JSONArray();

		List<AuthorTO> authorlist = null;
		
		try {
			usersessionutil.checkSession(request.getSession(false));
			authorlist = authorbo.fetchAuthor();

			for(AuthorTO author: authorlist) {
				authorto = author;
				json = new JSONObject();
				
				json.put("id", authorto.getAuthor_id());
				json.put("name", authorto.getName());

				jsonarray.put(json);
			}
			response.setContentType("application/json");
			response.getWriter().print(jsonarray);
		} catch (JSONException e) {
			response.setStatus(404);
		} catch (UserSessionException e) {
			response.setStatus(404);
		} catch (AuthorException e) {
			response.setStatus(404);
			response.setContentType("application/text");
			response.getWriter().print(e.getMessage());
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
