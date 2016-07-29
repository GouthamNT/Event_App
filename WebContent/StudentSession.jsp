<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Create Session</title>
<link rel="stylesheet" href="EventCreation.css">
<link rel="stylesheet" href="StudentSession.css">
<script src="jquery-1.12.3.js"></script>
</head>
<body>
	<div id=Main-content>
		<p id=heading>Session Details</p>
		<p id=sub-heading>Event ID : ${requestScope.eventid}</p>
		<p id=sub-heading>Event Name : ${requestScope.eventname}</p>
		<table id=input-attribute>
			<thead>
				<tr class="data">
					<td>Title</td>
					<td>Author</td>
					<td>Duration</td>
					<td>Details</td>
					<td>Rating</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="session" items="${requestScope.session}"
					varStatus="count">
					<tr class="data"
						data-session_${count.index} ="${session.session_id }">
						<td><c:out value="${session.title }" /></td>
						<td><c:out value="${session.author }" /></td>
						<td><c:out value="${session.duration }" /></td>
						<td><c:out value="${session.description }" /></td>
						<td><c:if test="${session.rating le 0}">
								<span id=down-vote_${count.index} index=${count.index
									} class=down-arrow>
                        </span>
							</c:if> <span id=rating_${count.index}><c:out
									value="${session.rating }" /></span> <c:if
								test="${session.rating le 0}">
								<span id=up-vote_${count.index} index=${count.index
									} class=up-arrow></span>
								<span><button type="button" id=confirm
										index=${count.index } class=small>Confirm</button></span>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<script src=StudentSession.js></script>
</body>
</html>