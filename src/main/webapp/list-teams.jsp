<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Select Team</title>
</head>
<body>
    <h1>Select a Team to View Players</h1>
    <div>
        <%-- Iterate over the teams and create a button for each --%>
        <c:forEach items="${teams}" var="team">
            <form action="viewPlayersByTeamServlet" method="get">
                <input type="hidden" name="teamName" value="${team}">
                <button type="submit">${team}</button>
            </form>
        </c:forEach>
    </div>
    <a href="index.html">Back to Home</a>
</body>
</html>
