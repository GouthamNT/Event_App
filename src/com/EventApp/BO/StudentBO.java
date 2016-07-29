package com.EventApp.BO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.EventApp.Constants.ErrorConstants;
import com.EventApp.DAO.IStudent;
import com.EventApp.DAO.StudentDAO;
import com.EventApp.Exception.StudentException;
import com.EventApp.TO.StudentTO;

public class StudentBO {
	IStudent studentdao = new StudentDAO();
	Map<String,String> errormap = new HashMap<String,String>();

	private boolean checkUserName(StudentTO studentto) {

		boolean checkusername = studentdao.validateUsername(studentto);

		return checkusername;
	}

	public void addStudent(StudentTO studentto) throws StudentException {
		boolean checkusername = checkUserName(studentto);
		if(!checkusername) {
			errormap.put(ErrorConstants.USERNAME_STUDENT_EXIST_ERROR, ErrorConstants.USERNAME_STUDENT_EXIST_ERROR_MESSAGE);
		} else {
			studentdao.addStudent(studentto);
		}

		if(errormap.size() > 0) {
			throw new StudentException(errormap);
		}
		
	}

	public List<StudentTO> fetchStudent() throws StudentException {

		List<StudentTO> studentlist = new ArrayList<StudentTO>();
		studentlist = studentdao.fetchStudent();
		if(!(studentlist.size() > 0)) {
			throw new StudentException(ErrorConstants.STUDENT_ASSIGNED_ERROR);
		}
		return studentlist;
	}
	
	public void viewStudent(StudentTO studentto) {

		studentdao.viewStudent(studentto);
	}

	public void assignStudentBatch(String[] students, StudentTO studentto) throws StudentException {

		if(students.length > 0) {
			
			studentdao.assignStudentBatch(students, studentto);
		} else {
			throw new StudentException(ErrorConstants.NO_STUDENT_SELECTED_ERROR_MESSAGE);
		}
	}

	public void updateStudent(StudentTO studentto) throws StudentException {
		
		studentdao.updateStudent(studentto);
	}

	public void getStudentID(StudentTO studentto) {

		studentdao.getStudentID(studentto);
	}
}
