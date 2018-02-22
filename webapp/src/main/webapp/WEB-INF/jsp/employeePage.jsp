<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee page</title>
    <link href="<c:url value="/css/employeePage.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/searchEmployee.jsp"  />
<br/>

    <h2 style="text-align:center">User Profile Card</h2>
<h2 style="text-align:center">::::::::::::</h2>

    <div class="card">
        <h1>${employee.surname} ${employee.name}</h1>
        <p class="title">Department id: ${employee.departmentId}</p>
        <p> </p>
        <div style="margin: 24px 0;">
            <a href="#"><i class="fa fa-dribbble"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-facebook"></i></a>
        </div>
        <p>${employee.phone}</p>
    </div>
<h3 style="text-align:center">
    <a href="<c:url value="/editEmployee?id=${employee.id}&name=${employee.name}&surname=${employee.surname}&phone=${employee.phone}" />">Edit employee</a>
    <a href="<c:url value="/addEmployee" />">Add employee</a>
</h3>

</body>
</html>
