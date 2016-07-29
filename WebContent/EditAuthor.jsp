<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <link rel="stylesheet" href="EventCreation.css">
        <script src="jquery-1.12.3.js"></script>
    </head>
    <body>
        <div id=Main-content>
            <p id=heading>Update Author Details</p>
            <form action=UpdateAuthor method=post>
            <input type=hidden name=author value='${requestScope.author.author_id }'>
            <input type=hidden name=action value=update>
            <table id=input-attribute>
                <tr>
                    <td>Name</td>
                    <td>:</td>
                    <td><input type=text name=name data-type=text value='${requestScope.author.name }'></td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td>:</td>
                    <td><input type=text name=age data-type=number value='${requestScope.author.age }'></td>
                </tr>
                <tr>
                    <td>Experience</td>
                    <td>:</td>
                    <td><input type=text name=experience data-type=number value='${requestScope.author.experience }'></td>
                </tr>
                <tr>
                    <td>E-mail ID</td>
                    <td>:</td>
                    <td><input type=text name=email data-type=email value='${requestScope.author.emailid }'></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>:</td>
                    <td><textarea name=address>${requestScope.author.address }</textarea></td>
                </tr>
            </table>
        <div id=button><button type="button" class="normal">Update</button></div>
        </form>
        </div>
        <script src="Validator.js"></script>
        <script src="FormValidator.js"></script>
    </body>
</html>