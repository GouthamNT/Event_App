package com.EventApp.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.EventApp.Constants.QueryConstants;
import com.EventApp.TO.RatingTO;
import com.EventApp.Util.DBUtil;

public class RatingDAO implements IRating{
	private Connection dbconnection = null;
	private PreparedStatement preparedstament = null;

	@Override
	public void updateStudentRating(RatingTO ratingto) {

		try {
			dbconnection = DBUtil.getConnection();
			//String sessionid = generateSessionId(sessionto.getEvent_id());
			preparedstament = dbconnection.prepareStatement(QueryConstants.INSERT_RATING);

			preparedstament.setString(1, ratingto.getSession_id());
			preparedstament.setString(2, ratingto.getUsername());
			preparedstament.setInt(3, ratingto.getRating());


			preparedstament.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
