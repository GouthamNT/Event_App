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

import com.EventApp.BO.StudentBO;
import com.EventApp.Constants.DatabaseContants;
import com.EventApp.Constants.KeywordConstants;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.StudentException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.StudentTO;
import com.EventApp.Util.LoginUtils;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentTO studentto;
	private StudentBO studentbo;
	UserSessionUtils usersessionutil = new UserSessionUtils();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		studentto = null;
		studentbo = new StudentBO();

		List<StudentTO> studentlist = new ArrayList<StudentTO>();

		JSONArray jsonarray = new JSONArray();
		JSONObject json = null;
		int length = studentlist.size();
		try {
			usersessionutil.checkSession(request.getSession(false));
			studentlist = studentbo.fetchStudent();
			for(int i = 0; i < length; i++) {
				json = new JSONObject();
				studentto = studentlist.get(i);

				json.put(DatabaseContants.STUDENT_NAME, studentto.getName());
				json.put(DatabaseContants.STUDENT_USERNAME, studentto.getUsername());

				jsonarray.put(json);
				
			}
			response.setContentType("application/json");
			response.getWriter().print(jsonarray);

		} catch (JSONException e) {
			response.setStatus(404);
		} catch (UserSessionException e) {
			response.setStatus(404);
		} catch (StudentException e) {
			response.setStatus(404);
			response.setContentType("application/text");
			response.getWriter().print(e.getMessage());
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		studentto = new StudentTO();
		studentbo = new StudentBO();

		String action = request.getParameter("action");
		String username = "";
		String password = "";
		String name = "";
		String age = "";
		String emailid = "";
		String address = "";

		username = request.getParameter("username");
		name = request.getParameter("name");
		password = request.getParameter("password");
		age = request.getParameter("age");
		emailid = request.getParameter("email");
		address = request.getParameter("address");

		studentto.setUsername(username);
		studentto.setAge(Integer.parseInt(age));
		studentto.setName(name);
		studentto.setEmailid(emailid);
		studentto.setAddress(address);
		if(action.equals("create")) {

			RequestDispatcher dispatcher = null;

			try {
				//studentbo.checkUserName(studentto);
				usersessionutil.checkSession(request.getSession(false));
				studentbo.addStudent(studentto);

				LoginUtils login = new LoginUtils();

				login.createLogin(username, password, KeywordConstants.USER_TYPE_STUDENT);
				dispatcher = request.getRequestDispatcher(ViewConstants.INDEX);
				dispatcher.forward(request, response);

			} catch (StudentException e) {
				dispatcher = request.getRequestDispatcher(ViewConstants.CREATE_STUDENT);
				request.setAttribute("error", e.getStudenterrormap());
				System.out.println(e.getStudenterrormap());
				dispatcher.forward(request, response);
			} catch (UserSessionException e) {
				RequestDispatcher dispatch = request.getRequestDispatcher(ViewConstants.ERROR);
				dispatch.forward(request, response);
			}
		}

	}

	protected StudentTO getStudentto() {
		return studentto;
	}

	protected StudentBO getStudentbo() {
		return studentbo;
	}




}
