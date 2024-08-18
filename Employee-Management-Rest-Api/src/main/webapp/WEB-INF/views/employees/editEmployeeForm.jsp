<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
    <h2>Update Employee</h2>
    <form action="/employees/updateEmployee" method="post">
        <input type="hidden" name="id" value="${employee.id}">
        
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="${employee.firstName}"><br><br>
        
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${employee.lastName}"><br><br>
        
        <label for="course">Email:</label>
        <input type="text" id="email" name="email" value="${employee.email}"><br><br>
        
        <input type="submit" value="Update Employee">
    </form>
</body>
</html>
