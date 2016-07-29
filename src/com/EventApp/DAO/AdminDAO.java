package com.EventApp.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EventApp.Constants.QueryConstants;
import com.EventApp.Util.DBUtil;

public class AdminDAO implements IAdmin {

	Connection dbconnection = null;
	PreparedStatement preparedstatement = null;

	@Override
	public List<String> fetchUsernames(String keyword) {
		List<String> usernames = new ArrayList<String>();

		try {
			dbconnection = DBUtil.getConnection();
			preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_ALL_USERNAME);
			preparedstatement.setString(1, "%"+keyword+"%");

			ResultSet result = preparedstatement.executeQuery();

			while(result.next()) {
				usernames.add(result.getString(1));
			}


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


		return usernames;
	}

	@Override
	public void deleteUser(String[] usernames) {
		try {
			dbconnection = DBUtil.getConnection();

			for(String username:usernames){
				preparedstatement = dbconnection.prepareStatement(QueryConstants.DELETE_USERNAME);
				preparedstatement.setString(1, username);
				preparedstatement.execute();
			}


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
