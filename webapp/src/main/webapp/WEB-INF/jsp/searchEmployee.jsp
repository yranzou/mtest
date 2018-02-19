<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Employee</title>
</head>
<body>

<form action="<c:url value="/doSearchEmployee"/>" method="post" id="searchForm">
    Search in: <select name="searchIn" form="searchForm">
    <option value="NAME">name</option>
    <option value="SURNAME">surname</option>
    <option value="PHONE">phone</option>
    <option value="DEPARTMENT">department</option>
    <option value="LEADER">leaders name</option>
</select><input type="text" name="searchValue">
    <input type="submit">
</form>
<br>

</body>
</html>
