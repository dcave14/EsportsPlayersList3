<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Teams</title>
</head>
<body>
    <h1>Team List</h1>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>City</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${teams}" var="team">
                <tr>
                    <td>${team.teamName}</td>
                    <td>${team.city}</td>
                    <td><a href="${pageContext.request.contextPath}/editTeamServlet?teamId=${team.id}">Edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/deleteTeamServlet?teamId=${team.id}" onclick="return confirm('Are you sure you want to delete this team?')">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/index.html">Back to Home</a>
</body>
</html>
