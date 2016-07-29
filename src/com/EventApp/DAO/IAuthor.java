package com.EventApp.DAO;

import java.util.List;

import com.EventApp.TO.AuthorTO;

public interface IAuthor {
	
	boolean checkUserName(AuthorTO authorto);
	void addAuthor(AuthorTO authorto);
	void updateAuthor(AuthorTO authorto);
	List<AuthorTO> fetchAuthor();
	public List<AuthorTO> viewAuthor();
	void editAuthor(AuthorTO authorto);
}
