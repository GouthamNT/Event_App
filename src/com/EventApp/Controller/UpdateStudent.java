package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.StudentBO;
import com.EventApp.Constants.ViewConstants;
import com.EventApp.Exception.StudentException;
import com.EventApp.TO.StudentTO;

/**
 * Servlet implementation class UpdateStudent
 */
@WebServlet("/UpdateStudent")
public class UpdateStudent extends StudentController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see StudentController#StudentController()
     */
    public UpdateStudent() {
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
		StudentTO studentto = getStudentto();
		StudentBO studentbo = getStudentbo();
		RequestDispatcher dispatcher = null;
		
		try {
			studentbo.updateStudent(studentto);
			dispatcher = request.getRequestDispatcher(ViewConstants.USER_INDEX);
			dispatcher.forward(request, response);
		} catch (StudentException e) {
			dispatcher = request.getRequestDispatcher(ViewConstants.ERROR);
			dispatcher.forward(request, response);
		}
	}

}
