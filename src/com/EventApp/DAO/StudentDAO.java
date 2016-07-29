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

import com.EventApp.Constants.QueryConstants;
import com.EventApp.TO.StudentTO;
import com.EventApp.Util.DBUtil;

public class StudentDAO implements IStudent {
	Connection dbconnection = null;
	PreparedStatement preparedstatement = null;

	@Override
	public boolean validateUsername(StudentTO studentto) {
		
		boolean flag = true;
		try {
			dbconnection = DBUtil.getConnection();

			preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_USERNAME);
			preparedstatement.setString(1, studentto.getUsername());

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
	public void getStudentID(StudentTO studentto) {
		
		ResultSet result = null;
		try {
			dbconnection = DBUtil.getConnection();

			preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_STUDENT_ID);
			preparedstatement.setString(1, studentto.getUsername());	
			
			result = preparedstatement.executeQuery();
			if(result.next()) {
				studentto.setStudent_id(result.getString(1));
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
	
	@Override
	public void addStudent(StudentTO studentto) {


		try {
			dbconnection = DBUtil.getConnection();

			String studentid = generateStudentId();
			preparedstatement = dbconnection.prepareStatement(QueryConstants.INSERT_STUDENT);
			preparedstatement.setString(1, studentid);
			preparedstatement.setString(2, studentto.getUsername());
			preparedstatement.setString(3, studentto.getName());
			preparedstatement.setInt(4, studentto.getAge());
			preparedstatement.setString(5, studentto.getEmailid());
			preparedstatement.setString(6, studentto.getAddress());
			preparedstatement.setString(7, "unassigned");

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

	private String generateStudentId() throws SQLException {
		String lastid = "";
		String newid = "";

		preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_LAST_STUDENT_ID);
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
				newid = "ST-001";
			}
		}

		return newid;
	}

	@Override
	public void updateStudent(StudentTO studentto) {
		try {
			dbconnection = DBUtil.getConnection();

			preparedstatement = dbconnection.prepareStatement(QueryConstants.UPDATE_STUDENT);
			preparedstatement.setString(1, studentto.getEmailid());
			preparedstatement.setString(2, studentto.getAddress());
			preparedstatement.setString(3, studentto.getStudent_id());

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
	public void viewStudent(StudentTO studentto) {
		try {
			dbconnection = DBUtil.getConnection();
			preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_STUDENT);
			preparedstatement.setString(1, studentto.getStudent_id());
			
			ResultSet rs = preparedstatement.executeQuery();
			
			if(rs.next()) {
				studentto.setName(rs.getString(1));
				studentto.setAge(rs.getInt(2));
				studentto.setEmailid(rs.getString(3));
				studentto.setAddress(rs.getString(4));
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

	@Override
	public List<StudentTO> fetchStudent() {
		StudentTO studentto = null;
		List<StudentTO> studentlist = new ArrayList<StudentTO>();
		
		try {
			dbconnection = DBUtil.getConnection();
			preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_STUDENTNAME);
			ResultSet rs = preparedstatement.executeQuery();
			
			while(rs.next()) {
				studentto = new StudentTO();
				studentto.setName(rs.getString(1));
				studentto.setUsername(rs.getString(2));
				
				studentlist.add(studentto);
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
		
		return studentlist;
	}

	@Override
	public void assignStudentBatch(String[] students, StudentTO studentto) {
		
		try {
			dbconnection = DBUtil.getConnection();
			String batchid = generateBatchId();
			studentto.setBatch_id(batchid);
			for (int i = 0; i < students.length; i++) {
				String[] tokens = students[i].split("-");
				String username = tokens[1];	
				preparedstatement = dbconnection.prepareStatement(QueryConstants.UPDATE_STUDENT_BATCH);
				preparedstatement.setString(1, batchid);
				preparedstatement.setString(2, username);
			
				preparedstatement.executeUpdate();
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
	private String generateBatchId() throws SQLException {
		String lastid = "";
		String newid = "";

		preparedstatement = dbconnection.prepareStatement(QueryConstants.FETCH_LAST_BATCH_ID);
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
				newid = "BAT-001";
			}
		}

		return newid;
	}

}
