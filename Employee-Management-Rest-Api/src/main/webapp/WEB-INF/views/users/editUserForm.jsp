<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
    <h2>Edit User</h2>
    <form:form action="update" method="POST" modelAttribute="user">
        <form:hidden path="id"/>
        <table>
            <tr>
                <td>Username:</td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save"/>
                    <a href="/users/list">Cancel</a>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
