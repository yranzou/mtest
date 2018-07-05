<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/headerEmp.jsp"/>

    <title>Display Employees</title>
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
            <div class="divTableCell">Employee</div>
            <div class="divTableCell">Department</div>
        </div>
        <c:forEach var="employee" items="${employees}" varStatus="status">
            <div class="divTableRow">
                <div class="divTableCell">${employee.id}</div>
                <div class="divTableCell">
                    <a href="<c:url value="/employee/${employee.id}" />">
                            ${employee.name} ${employee.surname}
                    </a>
                </div>
                <div class="divTableCell">
                    <a href="<c:url value="/department/${departments[status.index].id}" />">
                            ${departments[status.index].name}
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/bottom.jsp"/>
</body>
</html>
