<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Role to User</title>
    
    <script>
        function showRoleForm() {
        window.location.href = "/roles/${userId}/list";
            //document.getElementById("newRoleForm").style.display = "block";
        }
    </script>
</head>
<body>
    <h2>Add Role to User</h2>
    <form action="/userRoles/addUserRole" method="POST">
        <input type="hidden" name="userId" value="${userId}" />        
        <select name="roleId">
            <option value="">Select Role</option>
            <c:forEach items="${roles}" var="role">
                <option value="${role.id}">${role.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Add Role" />
        <a href="/users/list">Cancel</a>
    </form>
    <!-- Button to show the new role form -->
    <button type="button" onclick="showRoleForm()">Create New Role</button>

    <!-- Form to create a new role -->
    <div id="newRoleForm" style="display:none;">
        <h3>Create New Role</h3>
        <form action="/userRoles/create" method="POST">
            <input type="hidden" name="userIdInRoleForm" value="${userId}" />
            <input type="text" name="roleName" placeholder="Role Name" required />
            <input type="submit" value="Create Role" />
        </form>
    </div>
    <h3>Assigned Roles</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${assignedRoles}" var="assignedRole">
                <tr>
                    <td>${assignedRole.name}</td>
                    <td>
                        <form action="/userRoles/removeUserRole" method="POST">
                            <input type="hidden" name="userId" value="${userId}" />
                            <input type="hidden" name="roleId" value="${assignedRole.id}" />
                            <button type="submit">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
