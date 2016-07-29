package com.EventApp.BO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.EventApp.Constants.ErrorConstants;
import com.EventApp.DAO.AuthorDAO;
import com.EventApp.DAO.IAuthor;
import com.EventApp.Exception.AuthorException;
import com.EventApp.TO.AuthorTO;

public class AuthorBO {
	IAuthor authordao = new AuthorDAO();
	Map<String,String> errorMap = new HashMap<String,String>();

	public void addAuthor(AuthorTO authorto) {
		authordao.addAuthor(authorto);
	}

	public void updateAuthor(AuthorTO authorto) {
		authordao.updateAuthor(authorto);
	}

	public List<AuthorTO> viewAuthor() {
		List<AuthorTO> authorlist = new ArrayList<AuthorTO>();
		authorlist = authordao.viewAuthor();
		return authorlist;
	}

	public List<AuthorTO> fetchAuthor() throws AuthorException {
		List<AuthorTO> authorlist = new ArrayList<AuthorTO>();
		authorlist = authordao.fetchAuthor();
		
		if(authorlist.size() == 0) {
			throw new AuthorException(ErrorConstants.AUTHOR_UNAVAILABLE_ERROR_MESSAGE);
		}
		return authorlist;
	}

	public List<String> fetchAuthorNames() throws AuthorException {
		List<String> authorlist = new ArrayList<String>();
		authorlist = buildAuthorDropdown(authordao.fetchAuthor());
		return authorlist;
	}

	private List<String> buildAuthorDropdown(List<AuthorTO> authortoList) throws AuthorException {

		List<String> authorlist = new ArrayList<String>();
		int length = authortoList.size();

		String authorName = null;
		String authorID = null;
		String authorOption = null;

		if(length > 0) {

			for(AuthorTO authorto: authortoList) {
				authorName = authorto.getName();
				authorID = authorto.getAuthor_id();
				authorOption = authorName + "( " + authorID + " )";
				authorlist.add(authorOption);
			}
		} else {
			errorMap.put(ErrorConstants.AUTHOR_UNAVAILABLE, ErrorConstants.AUTHOR_UNAVAILABLE_ERROR_MESSAGE);
		}
		
		if(errorMap.size() > 0) {
			throw new AuthorException(errorMap);
		}
		
		return authorlist;
	}

	public void editAuthor(AuthorTO authorto) {
		authordao.editAuthor(authorto);		
	}
}
