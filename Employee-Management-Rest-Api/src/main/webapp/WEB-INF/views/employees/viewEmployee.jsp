<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h3>Employee Details</h3>
    <hr>
    <div class="row">
        <div class="col-md-6">
            <table class="table">
                <tr>
                    <th>ID:</th>
                    <td>${employee.id}</td>
                </tr>
                <tr>
                    <th>First Name:</th>
                    <td>${employee.firstName}</td>
                </tr>
                <tr>
                    <th>Last Name:</th>
                    <td>${employee.lastName}</td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td>${employee.email}</td>
                </tr>
            </table>
        </div>
    </div>   
    

    <hr>
    <c:url var="backUrl" value="/employees/list" />
    <a href="${backUrl}" class="btn btn-primary">Back to Employee List</a>
</div>
</body>
</html>
