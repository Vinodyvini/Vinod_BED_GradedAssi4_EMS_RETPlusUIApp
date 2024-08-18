<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Role</title>
</head>
<body>
    <h2>Create Role</h2>
    <form action="create" method="POST">
        <label for="roleName">Role Name:</label>
        <input type="text" id="roleName" name="name" required>
        <input type="submit" value="Create">
        <a href="/roles/${userId}/list">Cancel</a>
        <c:if test="${error == 'RoleNameRequired'}">
            <p style="color: red;">Role name is required.</p>
        </c:if>
        <c:if test="${error == 'RoleNameExists'}">
            <p style="color: red;">Role name already exists.</p>
        </c:if>
    </form>
</body>
</html>
