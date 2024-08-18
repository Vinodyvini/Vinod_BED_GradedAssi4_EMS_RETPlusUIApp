<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
</head>
<body>
    <h2>User Details</h2>
    <p><strong>Username:</strong> ${user.username}</p>
    <p><strong>Roles:</strong> 
        <c:forEach items="${user.roles}" var="role">
            ${role.name} 
        </c:forEach>
    </p>
    
<a href="/users/list">Back to User List</a>
</body>
</html>    