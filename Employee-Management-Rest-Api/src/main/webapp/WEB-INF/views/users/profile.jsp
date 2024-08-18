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
    <title>User Profile</title>
</head>
<body>
    
    
<div class="container">

      <h2>User Profile</h2>
      <hr>
      <form action="" class="form-inline">
            <p><strong>Username:</strong> ${user.username}</p>

            <!-- Add Logout button -->
            <c:url var="logoutUrl" value="/logout" />
            <a href="${logoutUrl}" class="btn btn-primary btn-sm mb-3 mx-auto">
                Logout </a>

        </form>
    
        <h3>Employee List</h3>
        <hr>

        <form action="" class="form-inline">

            <c:url var="addUrl" value="/employees/addEmployeeForm" />

            <!-- Add a button 
            <a href="${addUrl}" class="btn btn-primary btn-sm mb-3"> Add Employee </a>-->

            

        </form>
        
        
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Actions</th> <!-- Add a new column for actions -->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.email}</td>
                   <td>
                        <a href="/employees/viewEmployee?id=${employee.id}">View</a>
                         <!--<a href="editEmployeeForm?id=${employee.id}">Edit</a>
                        <a href="deleteEmployee?id=${employee.id}">Delete</a>
                        <a href="/userRoles/addRole/${employee.id}">Add Roles</a>-->
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
</body>
</html>
