<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Role</title>
</head>
<body>
    <h2>Edit Role</h2>
    <form:form action="update" method="POST" modelAttribute="role">
        <form:hidden path="id"/>
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save"/>
                    <a href="/roles/${userId}/list">Cancel</a>
                </td>
            </tr>
        </table>
        <c:if test="${error == 'RoleNameRequired'}">
            <p style="color: red;">Role name is required.</p>
        </c:if>
        <c:if test="${error == 'RoleNameExists'}">
            <p style="color: red;">Role name already exists.</p>
        </c:if>
        
    </form:form>
</body>
</html>
