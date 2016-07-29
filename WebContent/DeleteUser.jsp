<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete User</title>
<link rel="stylesheet" href="EventCreation.css">
<link rel="stylesheet" href="DeleteUser.css">
    <script src="jquery-1.12.3.js"></script>
</head>
<body>
<div id=searchBar>
<span class=Appicon>EventApp</span>
	<input type=text class=search id=searchUser name=searchUser placeholder="Search User">
	<span id=searchButton class="searchButton">Search</span>
</div>
    <form action=SearchUser id=search></form>
    <c:if test="${requestScope.result eq 1}">
        <div id=Main-content-delete>
            <p class=header>User</p>
            <form action=DeleteUser id=delete method="post">
            <div id=content-delete-data>
                <c:forEach var="username" items="${requestScope.username }">
    	           <p id=userData><input type=checkbox name=username value='${username}'>${username}</p>
                </c:forEach>
            </div>
            <button class=delete-user>Delete</button>
             </form>
        </div>
    </c:if>
    
    <script src=DeleteUser.js></script>
</body>
</html>