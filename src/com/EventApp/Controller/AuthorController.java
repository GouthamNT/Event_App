package com.EventApp.Controller;

import java.io.IOException;
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

import com.EventApp.BO.AuthorBO;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.AuthorTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class AuthorController
 */
@WebServlet("/AuthorController")
public class AuthorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthorTO authorto;
	private AuthorBO authorbo;
	UserSessionUtils usersessionutil = new UserSessionUtils();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		authorbo = new AuthorBO();
		authorto = null;

		JSONObject json = null;
		JSONArray jsonarray = new JSONArray();

		String action = request.getParameter("action");
		List<AuthorTO> authorlist = null;

		if(action.equals("viewauthor")) {
			authorlist = authorbo.viewAuthor();
		} 
		int length = authorlist.size();
		try {
			usersessionutil.checkSession(request.getSession(false));

			for(int i = 0; i < length; i++) {
				authorto = authorlist.get(i);
				json = new JSONObject();

				try {
					json.put("id", authorto.getAuthor_id());
					json.put("name", authorto.getName());
					json.put("age", authorto.getAge());
					json.put("exp", authorto.getExperience());
					json.put("email", authorto.getEmailid());
					json.put("address", authorto.getAddress());
					json.put("rating", authorto.getRating());

					jsonarray.put(json);
				} catch (JSONException e) {
					throw new UserSessionException();
				}
			}
			response.setContentType("application/json");
			response.getWriter().print(jsonarray);
		} catch (UserSessionException e1) {
			response.setStatus(404);
		}
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		authorto = new AuthorTO();
		authorbo = new AuthorBO();
		RequestDispatcher dispatch = null;
		
		try {
			usersessionutil.checkSession(request.getSession());

			String action = request.getParameter("action");
			String name = request.getParameter("name");
			String experience = request.getParameter("experience");
			String age = request.getParameter("age");
			String emailid = request.getParameter("email");
			String address = request.getParameter("address");

			authorto.setName(name);
			authorto.setAge(Integer.parseInt(age));
			authorto.setExperience(Integer.parseInt(experience));
			authorto.setEmailid(emailid);
			authorto.setAddress(address);
			setAuthorbo(authorbo);
			setAuthorto(authorto);
			if(action.equals("create")) {
				authorbo.addAuthor(authorto);
				dispatch = request.getRequestDispatcher(ViewConstants.ADMIN_INDEX);
				dispatch.forward(request, response);
			}
		} catch (UserSessionException e) {
			dispatch = request.getRequestDispatcher(ViewConstants.ERROR);
			dispatch.forward(request, response);
		}

	}


	protected AuthorTO getAuthorto() {
		return authorto;
	}

	protected void setAuthorto(AuthorTO authorto) {
		this.authorto = authorto;
	}

	protected AuthorBO getAuthorbo() {
		return authorbo;
	}

	protected void setAuthorbo(AuthorBO authorbo) {
		this.authorbo = authorbo;
	}

}
