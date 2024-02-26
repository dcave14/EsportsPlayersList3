<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Team</title>
</head>
<body>
    <h2>Add New Team</h2>
    <form action="${pageContext.request.contextPath}/addTeamServlet" method="post">
        Team Name: <input type="text" name="name" required><br>
        City: <input type="text" name="city" required><br>
        <input type="submit" value="Add Team">
    </form>
    <a href="${pageContext.request.contextPath}/viewTeamsServlet">View Teams</a>
    <br>
    <a href="index.html">Back to Home</a>
</body>
</html>
