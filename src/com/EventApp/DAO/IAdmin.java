package com.EventApp.DAO;

import java.util.List;

public interface IAdmin {

	List<String> fetchUsernames(String keyword);
	void deleteUser(String[] usernames);
}
