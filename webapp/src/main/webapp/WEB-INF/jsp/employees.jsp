<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Display Employees</title>
</head>
<body>
<table>
        <%--<jsp:useBean id="employeeService" scope="application" class="com.mtest.server.EmployeeService"/>--%>
        <%--<c:set var="employees" value="${employeeService.getAllEmployees}"/>--%>
        <c:forEach var="employee" items="${employees}">
<tr>
    <td>${employee.id}</td>
    <td>${employee.name}</td>
    <td>${employee.surname}</td>
    <td>${employee.phone}</td>
    <td>${employee.departmentId}</td>
    <td>-</td>
</tr>
        </c:forEach>
    <%--<% } %>--%>
</table>
</body>
</html>
