package com.EventApp.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.EventApp.BO.RatingBO;
import com.EventApp.TO.RatingTO;
import com.EventApp.TO.StudentTO;

/**
 * Servlet implementation class RatingController
 */
@WebServlet("/RatingController")
public class RatingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingController() {
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
		RatingTO ratingto = new RatingTO();
		RatingBO ratingbo = new RatingBO();
	
		String rating = request.getParameter("rating");
		String sessionid = request.getParameter("sessionid");
		
		HttpSession session = request.getSession(false);
		StudentTO studentto = (StudentTO) session.getAttribute("student");
		String username = studentto.getUsername();
		
		ratingto.setRating(Integer.parseInt(rating));
		ratingto.setUsername(username);
		ratingto.setSession_id(sessionid);
		
		ratingbo.updateRating(ratingto);
		
	}

}
