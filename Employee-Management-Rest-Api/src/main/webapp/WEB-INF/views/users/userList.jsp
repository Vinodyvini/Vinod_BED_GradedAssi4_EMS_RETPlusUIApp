<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
    integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
    crossorigin="anonymous">
    <title>User List</title>
</head>
<body>
<div class="container">

        <h2>User List</h2>
        <hr>

        <form action="" class="form-inline">


            <!-- Add Logout button -->
            <c:url var="logoutUrl" value="/logout" />
            <a href="${logoutUrl}" class="btn btn-primary btn-sm mb-3 mx-auto">
                Logout </a>

        </form>



    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Password</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>
                        <a href="editUserForm?id=${user.id}">Edit</a>
                        <a href="${user.id}">View</a>
                        <a href="delete/${user.id}">Delete</a>
                        <a href="/userRoles/addRole/${user.id}">Add Roles</a>
                        <a href="/employees/list">Add/Show Employee</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="addUserForm">Add User</a>
</div>
</body>
</html>
