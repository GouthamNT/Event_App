<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Edit Profile</title>
        <link rel="stylesheet" href="EventCreation.css">
        <script src="jquery-1.12.3.js"></script>
    </head>
    <body>
        <div id=Main-content>
            <p id=heading>Update Profile</p>
            <table id=input-attribute>
                <tr>
                    <td>Name</td>
                    <td>:</td>
                    <td>Test</td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td>:</td>
                    <td>22</td>
                </tr>
                <tr>
                    <td>E-mail ID</td>
                    <td>:</td>
                    <td><input type=text name=email value='${requestScope.student.emailid }'></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>:</td>
                    <td><textarea name=address>${requestScope.student.address }</textarea></td>
                </tr>
            </table>
        <div id=button><button type="button" class="normal" value=>Update</button></div>
            <script src="Validator.js"></script>
            <script src="FormValidator.js"></script>
        </div>
    </body>
</html>