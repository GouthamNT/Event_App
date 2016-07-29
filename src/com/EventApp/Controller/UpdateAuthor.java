package com.EventApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EventApp.BO.AuthorBO;
import com.EventApp.TO.AuthorTO;

/**
 * Servlet implementation class UpdateAuthor
 */
@WebServlet("/UpdateAuthor")
public class UpdateAuthor extends AuthorController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAuthor() {
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
		AuthorBO authorbo = getAuthorbo();
		AuthorTO authorto = getAuthorto();
		RequestDispatcher dispatch = null;
		
		String authorid = request.getParameter("author");
		authorto.setAuthor_id(authorid);
		
		authorbo.updateAuthor(authorto);
		
		dispatch = request.getRequestDispatcher("AdminIndex.jsp");
		dispatch.forward(request, response);
		
	}

}
