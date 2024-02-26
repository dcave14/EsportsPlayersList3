<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Team</title>
</head>
<body>
    <h2>Edit Team</h2>
    <form action="${pageContext.request.contextPath}/editTeamServlet" method="post">
        <input type="hidden" name="teamId" value="${team.id}">
        Team Name: <input type="text" name="name" value="${team.teamName}" required><br>
        City: <input type="text" name="city" value="${team.city}" required><br>
        <input type="submit" value="Update Team">
    </form>
    <a href="${pageContext.request.contextPath}/viewTeamsServlet">Back to Team List</a>
</body>
</html>
