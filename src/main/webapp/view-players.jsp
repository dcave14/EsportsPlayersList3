<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Players</title>
</head>
<body>
    <h1>Player List</h1>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Team</th>
                <th>Role</th>
                <th>State</th>
            </tr>
        </thead>
        <tbody>
			<c:forEach items="${players}" var="player">
			    <tr>
			        <td>${player.name}</td>
					<td>${player.team.teamName}</td>
			        <td>${player.role}</td>
			        <td>${player.state}</td>
			        <td><a href="${pageContext.request.contextPath}/editPlayerServlet?playerId=${player.id}">Edit</a></td>
			        <td><a href="${pageContext.request.contextPath}/deletePlayerServlet?playerId=${player.id}">Delete</a></td>
			    </tr>
			</c:forEach>
        </tbody>
    </table>
        <a href="index.html">Back to Home</a>
</body>
</html>