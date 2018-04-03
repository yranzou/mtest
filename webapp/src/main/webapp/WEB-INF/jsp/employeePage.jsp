<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee page</title>
    <link href="<c:url value="/css/employeePage.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/searchEmployee.jsp"/>
<br/>

<h2 style="text-align:center">User Profile Card</h2>
<h2 style="text-align:center">::::::::::::</h2>

<div class="card">
    <br/>
    <img src=${image} />

    <h1>User: ${employee.surname} ${employee.name}</h1>
    <p class="title">Department: ${employee.departmentId}</p>
    <p></p>
    <div style="margin: 24px 0;">
        <a href="#"><i class="fa fa-dribbble"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-linkedin"></i></a>
        <a href="#"><i class="fa fa-facebook"></i></a>
    </div>
    <p>Phone: ${employee.phone}</p>
    <br/>
</div>
<h3 style="text-align:center">
    <a href="<c:url value="/employee/edit/${employee.id}" />">Edit
    <%--<a href="<c:url value="/editEmployee?id=${employee.id}&name=${employee.name}&surname=${employee.surname}&phone=${employee.phone}" />">Edit--%>
        </a>
    &nbsp;
    <a href="<c:url value="/employee/add" />">Add</a>
</h3>

</body>
</html>
