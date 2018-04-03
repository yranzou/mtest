<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Employees</title>
</head>
<body>
<form action="<c:url value="/employee/doAdd"/>" method="post" enctype="multipart/form-data">
    Name:    <input type="text" id="name" name="name" value=""/>
    Surname:    <input type="text" id="surname" name="surname" value=""/>
    Phone:    <input type="text" id="phone" name="phone" value=""/>
    <br/>
    Upload photo: <input type="file" id="photo" name="photo" />
    <input type="submit" name="add" value="Add"/>
</form>
</body>
</html>
