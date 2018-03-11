<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
</head>
<body>

<form action="<c:url value="/doEditEmployee"/>" method="post" enctype="multipart/form-data">
Name:    <input type="text" id="name" name="name" value="<%=request.getParameter("name")%>"/>
Surname:    <input type="text" id="surname" name="surname" value="<%=request.getParameter("surname")%>"/>
Phone:    <input type="text" id="phone" name="phone" value="<%=request.getParameter("phone")%>"/>
   <br/>
    Upload photo: <input type="file" id="photo" name="photo" />
    <input type="hidden" id="id" name="id" value="<%=request.getParameter("id")%>">
    <input type="submit" name="update" id="update" value="Update"/>
</form>
</body>
</html>
