package com.EventApp.BO;

import com.EventApp.DAO.IRating;
import com.EventApp.DAO.RatingDAO;
import com.EventApp.TO.RatingTO;

public class RatingBO {
	
	public void updateRating(RatingTO ratingto){
		IRating ratingdao = new RatingDAO();
		ratingdao.updateStudentRating(ratingto);
	}

}
