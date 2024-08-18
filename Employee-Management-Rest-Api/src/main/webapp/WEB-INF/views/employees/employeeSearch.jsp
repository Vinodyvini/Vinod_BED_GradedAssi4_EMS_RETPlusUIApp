<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Employees</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Search Employees by First Name</h2>
    <form action="/employees/search" method="get" class="form-inline">
        <input type="text" name="firstName" placeholder="First Name" value="${firstName}" class="form-control mb-2 mr-sm-2">
        <input type="submit" value="Search" class="btn btn-primary mb-2">
    </form>
    <h3>Employee List</h3>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.email}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${totalPages > 1}">
        <nav>
            <ul class="pagination">
                <c:forEach begin="0" end="${totalPages - 1}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?firstName=${firstName}&page=${i}&size=${size}">${i + 1}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>
    <a href="/employees/list" class="btn btn-primary">Back to Employee List</a>
</div>
</body>
</html>
