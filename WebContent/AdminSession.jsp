<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Session Details</title>
<link rel="stylesheet" href="EventCreation.css">
<script src="jquery-1.12.3.js"></script>
<script src="mustache.js"></script>
</head>
<body>
	<div id=Main-content>
		<p id=heading>List of Session</p>
		<button type="button" id=test>test</button>
		<p id=eventid>Event ID : ${requestScope.eventid}</p>
		<p id=sub-heading>Event Name : ${requestScope.eventname}</p>
		<form action=DeleteSession id=DeleteSession method="post"></form>
			<table id=input-attribute>
				<thead>
					<tr>
						<td>Title</td>
						<td>Author</td>
						<td>Duration</td>
						<td>Description</td>
						<td>Edit Session</td>
						<td>Delete Session</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="session" items="${requestScope.session}"
						varStatus="count">
						<tr	data-session_${count.index}="${session.session_id }">
							<td id=title_${count.index}><c:out value="${session.title }" /></td>
							<td id=author_${count.index} data-id='${session.author_id }'><c:out
								value="${session.author }"/></td>
							<td id=duration_${count.index}><c:out value="${session.duration }" /></td>
							<td id=description_${count.index}><c:out
									value="${session.description }" /></td>
							<td id=action_${count.index}><button id=edit_${count.index} type=button
									index="${count.index}" class="small">Edit</button></td>
							<td><button type=button id=delete_${count.index} class="small" index="${count.index}">Delete</button></td>
						</tr>
					</c:forEach>
				</tbody>
            </table>
		<script src="Validator.js"></script>
		<script src="AdminSession.js"></script>
        <script src="Template.js"></script>
        <script src="Modal.js"></script>
	</div>
</body>
</html>