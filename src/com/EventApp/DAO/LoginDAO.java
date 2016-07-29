package com.EventApp.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.EventApp.Constants.QueryConstants;
import com.EventApp.TO.LoginTO;
import com.EventApp.Util.DBUtil;

public class LoginDAO implements ILogin {
	PreparedStatement preparedstatement = null;
	Connection dbconnection = null;

	@Override
	public boolean fetchCredentials(LoginTO loginto) {

		boolean flag = false;
		try {
			dbconnection = DBUtil.getConnection();
			preparedstatement = dbconnection.prepareStatement(QueryConstants.CHECK_CREDENTIALS);
			preparedstatement.setString(1, loginto.getUsername());
			preparedstatement.setString(2, loginto.getPassword());

			ResultSet result = preparedstatement.executeQuery();
			if(result.next()) {
				loginto.setUsertype(result.getString(3));
				flag = true;
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
		return flag;
	}

	public boolean validateUsername(String username) {

		boolean flag = true;
		try {
			dbconnection = DBUtil.getConnection();

			preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_USERNAME);
			preparedstatement.setString(1, username);

			ResultSet result = preparedstatement.executeQuery();

			if(result.next()) {
				flag = false;
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
		return flag;
	}

	@Override
	public void createCredentials(LoginTO loginto) {


		try {
			dbconnection = DBUtil.getConnection();
			preparedstatement = dbconnection.prepareStatement(QueryConstants.INSERT_CREDENTIALS);
			preparedstatement.setString(1, loginto.getUsername());
			preparedstatement.setString(2, loginto.getPassword());
			preparedstatement.setString(3, loginto.getUsertype());

			preparedstatement.executeUpdate();

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
