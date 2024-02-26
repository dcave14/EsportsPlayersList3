<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Players by Team</title>
</head>
<body>
    <h1>Select a Team to View Players</h1>
    <form action="${pageContext.request.contextPath}/viewPlayersByTeamServlet" method="get">
        <select name="teamId">
            <option value="">Select a team</option>
            <c:forEach items="${teams}" var="team">
                <option value="${team.id}" ${team.id == param.teamId ? 'selected' : ''}>${team.teamName}</option>
            </c:forEach>
        </select>
        <input type="submit" value="View Players">
    </form>

    <c:if test="${not empty playersByTeam}">
        <h2>Players for Team: ${teamName}</h2>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Team</th>
                    <th>Role</th>
                    <th>State</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${playersByTeam}" var="player">
                    <tr>
                        <td>${player.name}</td>
                        <td>${player.team.teamName}</td>
                        <td>${player.role}</td>
                        <td>${player.state}</td>
                        <td><a href="${pageContext.request.contextPath}/editPlayerServlet?playerId=${player.id}">Edit</a></td>
                        <td><a href="${pageContext.request.contextPath}/deletePlayerServlet?playerId=${player.id}" onclick="return confirm('Are you sure?')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <a href="${pageContext.request.contextPath}/index.html">Back to Home</a>
</body>
</html>
