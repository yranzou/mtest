<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
</head>
<body>

<form action="<c:url value="/doEditEmployee"/>" method="post">
Name:    <input type="text" name="name" value="<%=request.getParameter("name")%>"/>
Surname:    <input type="text" name="surname" value="<%=request.getParameter("surname")%>"/>
Phone:    <input type="text" name="phone" value="<%=request.getParameter("phone")%>"/>
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    <input type="submit" name="update" value="Update"/>
</form>
</body>
</html>
