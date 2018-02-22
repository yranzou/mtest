<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Employees</title>
    <link href="<c:url value="/css/employees.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/searchEmployee.jsp"  />

<div class="divTable">
    <div class="divTableBody">
        <div class="divTableRow">
            <div class="divTableCell">Id</div>
            <div class="divTableCell">Name</div>
            <div class="divTableCell">Surname</div>
            <div class="divTableCell">Private phone</div>
            <div class="divTableCell">Department</div>
        </div>
    <c:forEach var="employee" items="${employees}">
        <a href="<c:url value="/employeePage?id=${employee.id}" />">
            <div class="divTableRow">
                <div class="divTableCell">${employee.id}</div>
                <div class="divTableCell">${employee.name}</div>
                <div class="divTableCell">${employee.surname}</div>
                <div class="divTableCell">${employee.phone}</div>
                <div class="divTableCell">${department.name}</div>
            </div>
        </a>

    </c:forEach>
    <%--<% } %>--%>
    </div>
</div>

<a href="<c:url value="/addEmployee" />">Add employee</a>
</body>
</html>
