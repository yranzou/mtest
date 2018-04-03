<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
</head>
<body>

<form action="<c:url value="/employee/update"/>" method="post" enctype="multipart/form-data">
Name:    <input type="text" id="name" name="name" value="${employee.name}"/>
Surname:    <input type="text" id="surname" name="surname" value="${employee.surname}"/>
Phone:    <input type="text" id="phone" name="phone" value="${employee.phone}"/>
   <br/>
    Upload photo: <input type="file" id="photo" name="photo" />
    <input type="hidden" id="id" name="id" value="${employee.id}">
    <input type="submit" name="update" id="update" value="Update"/>
</form>
</body>
</html>
