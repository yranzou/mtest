<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Employee</title>
</head>
<body>
<form action="<c:url value="/doSearchEmployee"/>" method="post"><div>
    Search by name:<br>
    <input type="text" name="name"/></div>
    <br>
    <div>Search by surname:<br>
    <input type="text" name="surname"/></div>
    <%--<input type="text" name="phone"/>--%>
    <input type="submit" name="search" value="Search"/>
</form>
</body>
</html>
