<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
</head>
<body>

<form action="<c:url value="/doEditEmployee"/>" method="post">
Name:    <input type="text" name="name" value=""/>
Surname:    <input type="text" name="surname" value=""/>
Phone:    <input type="text" name="phone" value=""/>
    <input type="submit" name="add" value="Update"/>
</form>
</body>
</html>
