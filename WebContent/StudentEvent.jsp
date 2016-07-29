<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Student Event</title>
        <link rel="stylesheet" href="EventCreation.css">
    </head>
    <body>
        <form action=LoginController method="post">
            <input type="submit">
        </form>
        <div id=Main-content>
            <p id=heading>Events Registered</p>
            <form action=SessionController>
                <c:set var="event" value="${sessionScope.events}" />
            <table id=input-attribute>
                <thead>
                    <tr class="data">
                        <td>Name</td>
                        <td>Date</td>
                        <td>Location</td>
                        <td>Session Details</td>
                    </tr>
                </thead>
                <tbody>
                <tr class="data">
                <input type=hidden name=event value="${event.event_id}">
                    <td><c:out value="${event.name}" /></td>
                    <td><c:out value="${event.date}" /></td>
                    <td><c:out value="${event.address}"/><br><c:out value="${event.city} ${event.pin}" /></td>
                    <td><button type="submit" class="small">view Session</td>
                </tr>
                </tbody>
            </table>
            </form>
        </div>
    </body>
</html>