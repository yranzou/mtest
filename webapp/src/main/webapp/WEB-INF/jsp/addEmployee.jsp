<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Employees</title>
</head>
<body>
<form action="<c:url value="/doAddEmployee"/>" method="post">
    <input type="text" name="name"/>
    <input type="text" name="surname"/>
    <input type="text" name="phone"/>
    <input type="submit" name="add" value="Add"/>
</form>
</body>
</html>
