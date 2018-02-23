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

<h2 style="text-align:center">Department info:</h2>
<h2 style="text-align:center"></h2>

<div class="card">
    <br/>
    <h1>${department.name}</h1>
    <%--<p class="title">Department: ${employee.departmentId}</p>--%>
    <p></p>

    <p>Chief: ${departmentChief.surname}&nbsp;${departmentChief.name}</p>
    <br/>
</div>
<h3 style="text-align:center">
    <a href="<c:url value="/editDepartment?id=${department.id}&name=${department.name}&chiefId=${department.chiefId}" />">Edit
        </a>&nbsp;
    <a href="<c:url value="/addDepartment" />">Add</a>
</h3>

</body>
</html>
