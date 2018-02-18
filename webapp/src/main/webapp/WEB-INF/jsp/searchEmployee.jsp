<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Employee</title>
</head>
<body>
<form action="<c:url value="/doSearchEmployee"/>" method="post"><div>
    Search by name/surname:<br>
    <input type="text" name="name"/></div>
    <br>

    <%--<input type="text" name="phone"/>--%>
    <input type="submit" name="name" value="Search"/>
</form>
</body>
</html>
