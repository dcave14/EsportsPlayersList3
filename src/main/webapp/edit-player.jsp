<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Player</title>
</head>
<body>
    <h1>Edit Player</h1>
    <form action="editPlayerServlet" method="post">
        <input type="hidden" name="playerId" value="${player.id}">
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${player.name}" required>
        
		<label for="team">Team:</label>
		<select id="team" name="team" required>
		    <c:forEach items="${teams}" var="team">
		        <option value="${team.id}" ${player.team != null && player.team.id == team.id ? 'selected' : ''}>${team.teamName}</option>
		    </c:forEach>
		</select>

        
        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="SMG" ${player.role eq 'SMG' ? 'selected' : ''}>SMG</option>
            <option value="AR" ${player.role eq 'AR' ? 'selected' : ''}>AR</option>
            <option value="Flex" ${player.role eq 'Flex' ? 'selected' : ''}>Flex</option>
            <option value="Coach" ${player.role eq 'Coach' ? 'selected' : ''}>Coach</option>
        </select>
        
        <label for="state">State:</label>
        <select id="state" name="state" required>
            <option value="Active" ${player.state eq 'Active' ? 'selected' : ''}>Active</option>
            <option value="Substitute" ${player.state eq 'Substitute' ? 'selected' : ''}>Substitute</option>
            <option value="Inactive" ${player.state eq 'Inactive' ? 'selected' : ''}>Inactive</option>
        </select>
        
        <input type="submit" value="Save">
    </form>
    <a href="viewPlayersServlet">Cancel</a>
</body>
</html>