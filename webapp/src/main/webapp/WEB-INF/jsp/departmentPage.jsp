<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department inf</title>
    <link href="<c:url value="/css/employeePage.css"/>" rel="stylesheet" type="text/css">
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/top.jsp"/>
<br/>

<h2 style="text-align:center">Department info:</h2>
<h2 style="text-align:center"></h2>

<div class="card">
    <br/>
    <h1>${department.name}</h1>
    <%--<p class="title">Department: ${employee.departmentId}</p>--%>
    <p></p>

    <p>Chief: ${department.chief.name} ${department.chief.surname}</p>
    <br/>
</div>
<h3 style="text-align:center">
    <a href="<c:url value="/department/edit/${department.id}" />">Edit
    <%--<a href="<c:url value="/department/?id=${department.id}&name=${department.name}&chiefId=${department.chiefId}" />">Edit--%>
        </a>&nbsp;
    <a href="<c:url value="/department/add" />">Add</a>
</h3>
<jsp:include page="/WEB-INF/jsp/bottom.jsp"/>
</body>
</html>
