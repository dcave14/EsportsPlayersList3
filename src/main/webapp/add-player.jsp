<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Player</title>
</head>
<body>
    <h1>Add Player</h1>
    <form action="${pageContext.request.contextPath}/addPlayerServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>
        
        <label for="teamName">Team:</label><br>
        <c:forEach items="${teams}" var="team">
            <input type="radio" name="teamName" value="${team.teamName}" required> ${team.teamName}<br>
        </c:forEach>
        
        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="SMG">SMG</option>
            <option value="AR">AR</option>
            <option value="Flex">Flex</option>
            <option value="Coach">Coach</option>
        </select><br>
        
        <label for="state">State:</label>
        <select id="state" name="state" required>
            <option value="Active">Active</option>
            <option value="Substitute">Substitute</option>
            <option value="Inactive">Inactive</option>
        </select><br>
        
        <input type="submit" value="Save">
    </form>
    <a href="index.html">Cancel</a>
</body>
</html>