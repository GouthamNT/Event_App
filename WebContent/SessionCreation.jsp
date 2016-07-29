<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Create Session</title>
<link rel="stylesheet" href="EventCreation.css">
<script src="jquery-1.12.3.js"></script>
</head>
<body>
	<div id=Main-content>
		<p id=heading>Enter Session Details</p>
		<form action=SessionController method=post>
			<input type=hidden name=event value='${requestScope.event }'>
			<input type=hidden name=action value=create>
			<table id=input-attribute>
				<tr>
					<td>Title</td>
					<td>:</td>
					<td><input type=text name=title></td>
				</tr>
				<tr>
					<td>Author</td>
					<td>:</td>
					<td><select name=authorid>
					<option value=default>(Select)</option>
					<c:forEach items="${requestScope.authorOptions }" var="authorlist">
						<option value='${authorlist.author_id }'>${authorlist.name} ( ${authorlist.author_id} )</option>
					</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Duration</td>
					<td>:</td>
					<td><input type=text name=duration></td>
				</tr>
				<tr>
					<td>Details</td>
					<td>:</td>
					<td><textarea name=description></textarea></td>
				</tr>
			</table>
			<div>
				<button type="button" class="normal">Create</button>
			</div>
			<script src="Validator.js"></script>
			<script src="FormValidator.js"></script>
		</form>
	</div>
</body>
</html>