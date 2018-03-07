<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Employees</title>
</head>
<body>
<form action="<c:url value="/doAddEmployee"/>" method="post">
    Name: <input type="text" name="name"/>
    Surname: <input type="text" name="surname"/>
    Phone: <input type="text" name="phone"/>
    Upload photo: <input type="file" name="photo" />
    <input type="submit" name="add" value="Add"/>
</form>
</body>
</html>
