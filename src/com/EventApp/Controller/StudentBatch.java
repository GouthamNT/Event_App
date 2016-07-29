package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.EventApp.BO.StudentBO;
import com.EventApp.Exception.StudentException;
import com.EventApp.Exception.UserSessionException;
import com.EventApp.TO.StudentTO;
import com.EventApp.Util.UserSessionUtils;

/**
 * Servlet implementation class StudentBatch
 */
@WebServlet("/StudentBatch")
public class StudentBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentBatch() {
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
		StudentTO studentto = new StudentTO();
		StudentBO studentbo = new StudentBO();
		
		String[] students = request.getParameterValues("studentname[]");
		UserSessionUtils usersessionutil = new UserSessionUtils();
		try {
			usersessionutil.checkSession(request.getSession(false));
			studentbo.assignStudentBatch(students,studentto);
			JSONObject json = new JSONObject();
			json.put("success", studentto.getBatch_id());
			response.setContentType("application/json");
			response.getWriter().print(json);
		} catch (StudentException e) {
			response.setStatus(404);
			response.setContentType("application/text");
			response.getWriter().print(e.getMessage());
		} catch (UserSessionException e) {
			response.setStatus(404);
		} catch (JSONException e) {
			response.setStatus(404);
		}
	}

}
