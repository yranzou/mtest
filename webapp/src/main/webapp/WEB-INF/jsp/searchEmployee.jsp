<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--<title>Search Employee</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<c:if test = "${empty name}">--%>

<%--</c:if>--%>

<c:choose>
    <c:when test="${empty name}">
        <a href="<c:url value="/login" />">
            Login
        </a>
        <c:set var="isAuthenticated" scope="request" value="ui-state-disabled"/>
    </c:when>
    <c:otherwise>
        <a href="<c:url value="/logout" />">
            Logout
        </a>&nbsp;${name}
    </c:otherwise>
</c:choose>
&nbsp;

<div style="overflow: hidden;">
    <div style="width: 1000%;">

        <form action="<c:url value="/employee/search"/>" method="post" id="searchForm">
            <div style="float: left;">
                Search in: <select name="searchIn" form="searchForm">
                <option value="NAME">name</option>
                <option value="SURNAME">surname</option>
                <option value="PHONE">phone</option>
                <option value="DEPARTMENT">department</option>
                <option value="LEADER">leaders name</option>
            </select>
            </div>
            <div style="float: left;">
                <input type="text" name="searchValue">
            </div>
            <div class="widget" style="float: left;"><input type="submit" value="Find!">
            </div>
        </form>
    </div>
</div>
<br/>
<div class="divTableX">
    <div class="divTableBodyX">
        <div class="divTableRowX">
            <div class="divTableCellX">
                <ul id="menu">
                    <li>
                        <div>Employee</div>
                        <ul>

                            <li class="<c:out value = "${isAuthenticated}"/>">
                                <div>
                                    <a href="<c:url value="/employee/add"/>">
                                        Add new
                                    </a>
                                </div>
                            </li>
                            <li>
                                <div>
                                    <a href="<c:url value="/employee/all"/>">
                                        Show all
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <div>Department</div>
                        <ul>
                            <li class="<c:out value = "${isAuthenticated}"/>">
                                <div><a href="<c:url value="/department/add"/>">Add new</a></div>
                            </li>
                            <li>
                                <div><a href="<c:url value="/department/all"/>">Show all</a></div>

                        </ul>
                    </li>
                </ul>
            </div>
            <div class="divTableCellX">



