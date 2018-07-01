<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee profile</title>
    <link href="<c:url value="/css/employeePage.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/strippedtable.css"/>" rel="stylesheet" type="text/css">

    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/top.jsp"/>
<br/>
<div class="card">
    <div class="divTableX">
        <div class="divTableBodyX">
            <div class="divTableRowX">
                <div class="divTableCellX">
                    <div class="divTableX">
                        <div class="divTableBodyX">
                            <div class="divTableRowX">
                                <div class="divTableCellX">
                                    <img src="${image}" alt="employee photo"/>
                                    <h1>${employee.surname} ${employee.name}</h1>
                                    <h2 style="text-align:center"></h2>
                                </div>

                                <div class="divTableCellX">

                                </div>
                            </div>
                            <div class="divTableRowX">
                                <div class="divTableCellX">
                                    <div class="divTable paleBlueRows">
                                        <div class="divTableBody">
                                            <div class="divTableRow">
                                                <div class="divTableCell">Birthday</div>
                                                <div class="divTableCell">${birthDate}</div>
                                            </div>
                                            <div class="divTableRow">
                                                <div class="divTableCell">Department</div>
                                                <div class="divTableCell">
                                                    <a href="<c:url value="/department/${employee.department.id}"/>">
                                                        ${employee.department.name}
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="divTableRow">
                                                <div class="divTableCell">Manager</div>
                                                <div class="divTableCell">
                                                    <a href="<c:url value="${employee.chief.id}"/>">
                                                        ${employee.chief.name} ${employee.chief.surname}
                                                    </a>
                                                </div>
                                            </div>
                                        </div>

                                        <h3 style="text-align:center">
                                            <a href="<c:url value="/employee/edit/${employee.id}" />">Edit profile
                                            </a> <a href="<c:url value="/employee/delete/${employee.id}" />">Delete profile
                                        </a>

                                        </h3>
                                    </div>
                                </div>
                                <div class="divTableCellX">
                                    <div class="divTable paleBlueRows">
                                        <div class="divTableBody">
                                            <div class="divTableRow">
                                                <div class="divTableCell">Email</div>
                                                <div class="divTableCell">n/a</div>
                                            </div>
                                            <div class="divTableRow">
                                                <div class="divTableCell">Telegramm</div>
                                                <div class="divTableCell">n/a</div>
                                            </div>

                                            <c:forEach var="phone" items="${employee.phones}" varStatus="status">
                                                <div class="divTableRow">
                                                    <div class="divTableCell">${phone.type}</div>
                                                    <div class="divTableCell">${phone.number}</div>
                                                </div>

                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>
                <div class="divTableCellX">
                    <div id="reports" class="well">
                        <h3 class="modal-header">Subordinates</h3>
                        <ul class="nav nav-list">
                            <li>
                            </li>
                            <c:if test="${!empty employee.subordinates}">
                            <c:forEach var="subordinate" items="${employee.subordinates}" varStatus="status">
                            <li>
                                <a href="${subordinate.id}">
                                    <p class="list-item">${subordinate.name} ${subordinate.surname}<br/>
                                        <%--${subordinate.department.name}--%>
                                    </p>
                                </a>
                            </li>
                            </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/bottom.jsp"/>
</body>
</html>
