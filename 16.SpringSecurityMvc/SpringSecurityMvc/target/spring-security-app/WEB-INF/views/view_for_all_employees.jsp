<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>All info</title>
</head>
<body>

<h3>Information for all employees</h3>

<security:authorize access="hasRole('HR')">
<input type="button" value="Salary"
                    onclick="window.location.href = 'hr_info'">
Only for HR stuff
</security:authorize>

<br>
<br>

<security:authorize access="hasRole('MANAGER')">
<input type="button" value="Performance"
                    onclick="window.location.href = 'manager_info'">
Only for Manager stuff
</security:authorize>

</body>
</html>
