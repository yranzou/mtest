<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Employees</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/searchEmployee.jsp"  />

<table>
        <%--<jsp:useBean id="employeeService" scope="application" class="com.mtest.server.EmployeeService"/>--%>
        <%--<c:set var="employees" value="${employeeService.getAllEmployees}"/>--%>
        <c:forEach var="employee" items="${employees}">
            <a href="#">
<tr>
    <td><a href="<c:url value="/employeePage?id=${employee.id}" />">${employee.id}</a></td>
    <td>${employee.name}</td>
    <td>${employee.surname}</td>
    <td>${employee.phone}</td>
    <td>${employee.departmentId}</td>
</tr></a>

        </c:forEach>
    <%--<% } %>--%>
</table>
<a href="<c:url value="/addEmployee" />">Add employee</a>
</body>
</html>
