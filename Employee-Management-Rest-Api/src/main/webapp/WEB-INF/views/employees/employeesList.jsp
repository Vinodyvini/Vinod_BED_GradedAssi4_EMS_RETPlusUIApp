<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" crossorigin="anonymous">
<title>Employee List</title>

</head>
<body>
<div class="container">
    <h3>Employee List</h3>
    <hr>

    <form action="/employees/search" method="get" class="form-inline">
        <input type="text" name="firstName" placeholder="Search by First Name" class="form-control mb-2 mr-sm-2">
        <input type="submit" value="Search" class="btn btn-primary mb-2">
    </form>

    <form action="/employees/sort" method="get" class="form-inline">
        <select name="order" class="form-control mb-2 mr-sm-2">
            <option value="asc">Sort Ascending</option>
            <option value="desc">Sort Descending</option>
        </select>
        <input type="submit" value="Sort" class="btn btn-primary mb-2">
    </form>

    <c:url var="addUrl" value="/employees/addEmployeeForm" />
    <a href="${addUrl}" class="btn btn-primary btn-sm mb-3">Add Employee</a>

    <c:url var="logoutUrl" value="/logout" />
    <a href="${logoutUrl}" class="btn btn-primary btn-sm mb-3 mx-auto">Logout</a>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Actions</th>
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
                        <a href="viewEmployee?id=${employee.id}">View</a>
                        <a href="editEmployeeForm?id=${employee.id}">Edit</a>
                        <a href="deleteEmployee?id=${employee.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${totalPages > 1}">
        <div class="row">
            <div class="col-md-6">
                <p>Showing ${startRecord} to ${endRecord} of ${totalElements} records</p>
            </div>
            <div class="col-md-6">
                <nav>
                    <ul class="pagination justify-content-end">
                        <li class="page-item ${currentPage == 0 ? 'disabled' : ''}">
                            <a class="page-link" href="?page=${currentPage - 1}&size=${size}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach begin="0" end="${totalPages - 1}" var="i">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="?page=${i}&size=${size}">${i + 1}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
                            <a class="page-link" href="?page=${currentPage + 1}&size=${size}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </c:if>
    <a href="/users/list" class="btn btn-primary">Back to User List</a>
</div>
</body>
</html>
