package com.EventApp.DAO;

import java.util.List;

import com.EventApp.TO.StudentTO;

public interface IStudent {
	boolean validateUsername(StudentTO studentto);
	void addStudent(StudentTO studentto);
	void updateStudent(StudentTO studentto);
	void viewStudent(StudentTO studentto);
	List<StudentTO> fetchStudent();
	void assignStudentBatch(String[] students, StudentTO studentto);
	void getStudentID(StudentTO studentto);
	
}
