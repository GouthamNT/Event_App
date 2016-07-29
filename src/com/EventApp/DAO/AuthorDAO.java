package com.EventApp.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.EventApp.Constants.KeywordConstants;
import com.EventApp.Constants.QueryConstants;
import com.EventApp.TO.AuthorTO;
import com.EventApp.Util.DBUtil;

public class AuthorDAO implements IAuthor {

	Connection dbconnection = null;
	PreparedStatement preparedstatement = null;

	@Override
	public boolean checkUserName(AuthorTO authorto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addAuthor(AuthorTO authorto) {
		try {
			dbconnection = DBUtil.getConnection();

			String authorid = generateAuthorId();
			preparedstatement = dbconnection.prepareStatement(QueryConstants.INSERT_AUTHOR);
			preparedstatement.setString(1, authorid);
			preparedstatement.setString(2, authorto.getName());
			preparedstatement.setInt(3, authorto.getAge());
			preparedstatement.setInt(4, authorto.getExperience());
			preparedstatement.setString(5, authorto.getEmailid());
			preparedstatement.setString(6, authorto.getAddress());
			preparedstatement.setInt(7, KeywordConstants.DEFAULT_RATING);

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

	private String generateAuthorId() throws SQLException {
		String lastid = "";
		String newid = "";

		preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_LAST_AUTHOR_ID);
		ResultSet rs = preparedstatement.executeQuery();
		if(rs.next()) {
			lastid = rs.getString(1);
			if(lastid != null) {
				String[] idcomponents = lastid.split("-");
				String type = idcomponents[0];
				int numcomponent = Integer.parseInt(idcomponents[1]) + 1;
				NumberFormat formatter = new DecimalFormat("000");
				String series = formatter.format(numcomponent);
				newid = type + "-" + series;
			} else {
				newid = "AU-001";
			}
		}

		return newid;
	}

	@Override
	public void updateAuthor(AuthorTO authorto) {
		try {
			dbconnection = DBUtil.getConnection();

			preparedstatement = dbconnection.prepareStatement(QueryConstants.UPDATE_AUTHOR);
			preparedstatement.setString(1, authorto.getName());
			preparedstatement.setInt(2, authorto.getAge());
			preparedstatement.setInt(3, authorto.getExperience());
			preparedstatement.setString(4, authorto.getEmailid());
			preparedstatement.setString(5, authorto.getAddress());
			preparedstatement.setString(6, authorto.getAuthor_id());

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

	@Override
	public List<AuthorTO> fetchAuthor() {
		AuthorTO authorto = null;

		List<AuthorTO> authorlist = new ArrayList<AuthorTO>();

		String authorid = "";
		String authorname = "";
		int age;
		int experience;
		String email = "";
		String address = "";
		int rating;

		try {
			dbconnection = DBUtil.getConnection();

			preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_AUTHOR_UNASSIGNED);

			ResultSet resultset = preparedstatement.executeQuery();

			while(resultset.next()) {
				authorto = new AuthorTO();

				authorid = resultset.getString(1);
				authorname = resultset.getString(2);
				age = resultset.getInt(3);
				experience = resultset.getInt(4);
				email = resultset.getString(5);
				address = resultset.getString(6);
				rating = resultset.getInt(7);

				authorto.setAuthor_id(authorid);
				authorto.setName(authorname);
				authorto.setAge(age);
				authorto.setExperience(experience);
				authorto.setEmailid(email);
				authorto.setAddress(address);
				authorto.setRating(rating);

				authorlist.add(authorto);
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
		return authorlist;
	}

	@Override
	public List<AuthorTO> viewAuthor() {
		AuthorTO authorto = null;

		List<AuthorTO> authorlist = new ArrayList<AuthorTO>();

		String authorid = "";
		String authorname = "";
		int age;
		int experience;
		String email = "";
		String address = "";
		int rating;

		try {
			dbconnection = DBUtil.getConnection();

			preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_AUTHOR);

			ResultSet resultset = preparedstatement.executeQuery();

			while(resultset.next()) {
				authorto = new AuthorTO();

				authorid = resultset.getString(1);
				authorname = resultset.getString(2);
				age = resultset.getInt(3);
				experience = resultset.getInt(4);
				email = resultset.getString(5);
				address = resultset.getString(6);
				rating = resultset.getInt(7);

				authorto.setAuthor_id(authorid);
				authorto.setName(authorname);
				authorto.setAge(age);
				authorto.setExperience(experience);
				authorto.setEmailid(email);
				authorto.setAddress(address);
				authorto.setRating(rating);

				authorlist.add(authorto);
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
		return authorlist;

	}

	@Override
	public void editAuthor(AuthorTO authorto) {
		String authorid = "";
		String authorname = "";
		int age;
		int experience;
		String email = "";
		String address = "";
		int rating;

		try {
			dbconnection = DBUtil.getConnection();

			preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_AUTHOR_SINGLE);
			preparedstatement.setString(1, authorto.getAuthor_id());
			
			ResultSet resultset = preparedstatement.executeQuery();

			if(resultset.next()) {

				authorid = resultset.getString(1);
				authorname = resultset.getString(2);
				age = resultset.getInt(3);
				experience = resultset.getInt(4);
				email = resultset.getString(5);
				address = resultset.getString(6);
				rating = resultset.getInt(7);

				authorto.setAuthor_id(authorid);
				authorto.setName(authorname);
				authorto.setAge(age);
				authorto.setExperience(experience);
				authorto.setEmailid(email);
				authorto.setAddress(address);
				authorto.setRating(rating);

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
