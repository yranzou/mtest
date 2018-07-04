<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/headerEmp.jsp"/>

    <title>Departments</title>
    <link href="<c:url value="/css/employees.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/strippedtable.css"/>" rel="stylesheet" type="text/css">

    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/top.jsp"/>

<div class="divTable paleBlueRows">
    <div class="divTableBody">
        <div class="divTableRow">
            <div class="divTableCell">Id</div>
            <div class="divTableCell">Department</div>
            <div class="divTableCell">Chief</div>
        </div>
        <c:forEach var="department" items="${departments}" varStatus="status">
            <div class="divTableRow">
                <div class="divTableCell">${department.id}</div>
                <div class="divTableCell">
                    <a href="<c:url value="/department/${department.id}" />">
                            ${department.name}
                    </a>
                </div>
                <div class="divTableCell">
                    <a href="<c:url value="/employee/${department.chief.id}" />">
                            ${department.chief.name}
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/bottom.jsp"/>
</body>
</html>
