<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.EventApp.Constants.ErrorConstants" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="EventCreation.css">
<title>Insert title here</title>
</head>
<body>
<div id=Main-content>
		<p id=heading>Enter the Details</p>
		<form method=post action="StudentController">
		<input type=hidden name=action data-type=hidden value=create>
			<table id=input-attribute>
				<tr>
					<td>Name</td>
					<td>:</td>
					<td><input type=text name=name data-type=letter></td>
                </tr>
				<tr>
					<td>User Name</td>
					<td>:</td>
					<td><input type=text name=username></td>
					<td class=error-block>
					<c:if test="${requestScope.error[ErrorConstants.USERNAME_STUDENT_EXIST_ERROR] ne null}">
						<c:out value="${requestScope.error[ErrorConstants.USERNAME_STUDENT_EXIST_ERROR]}"></c:out>
					</c:if>
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td>:</td>
					<td><input type=password name=password></td>
				</tr>
				<tr>
					<td>Re-enter Password</td>
					<td>:</td>
					<td><input type=password name=reenterpassword></td>
				</tr>
				<tr>
					<td>Age</td>
					<td>:</td>
					<td><input type=text name=age data-type=number></td>
				</tr>
				<tr>
					<td>E-mail ID</td>
					<td>:</td>
					<td><input type=text name=email data-type=email></td>
				</tr>
				<tr>
					<td>Address</td>
					<td>:</td>
					<td><textarea name=address></textarea></td>
				</tr>
			</table>
			<div id=button>
				<button type="button" class="normal">Register</button>
			</div>
		</form>
        <script src="Validator.js"></script>
        <script src="FormValidator.js"></script>
	</div>
</body>
</html>