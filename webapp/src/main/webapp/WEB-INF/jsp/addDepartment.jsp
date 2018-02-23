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

<h2 style="text-align:center">Add department form</h2>
<h2 style="text-align:center"></h2>

<div class="card">

    <form action="<c:url value="/doAddDepartment"/>" method="post" id="addDepartmentForm">
        Select department name: <input type="text" name="name" value="${department.name}"/>

        <br/>
        Select chief name: <select name="chiefId" form="addDepartmentForm">
        <%--<option value="${department.chiefId}">${thisDepartmentChief.surname} &nbsp;${thisDepartmentChief.name}</option>--%>
        <c:forEach var="chief" items="${chiefs}" varStatus="status">
            <option value="${chief.id}">${chief.surname} &nbsp; ${chief.name}</option>
        </c:forEach>
        <option value="0">Without chief</option>
    </select>
        <input type="submit" name="add" value="Add"/>
    </form>
    <br/>
</div>
<h3 style="text-align:center">
    <%--<a href="<c:url value="/editDepartment?id=${department.id}&name=${department.name}&chiefId=${department.chiefId}" />">Edit--%>
    <%--</a>&nbsp;--%>
    <%--<a href="<c:url value="/addDepartment" />">Add</a>--%>
</h3>

</body>
</html>
