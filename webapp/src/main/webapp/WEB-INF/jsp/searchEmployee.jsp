<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Employee</title>
</head>
<body>
<%--<form action="<c:url value="/doSearchEmployee"/>" method="post"><div>--%>
    <%--Search by<br>--%>
    <%----%>
    <%--<input type="text" name="name"/></div>--%>
    <%--<br>--%>

    <%--&lt;%&ndash;<input type="text" name="phone"/>&ndash;%&gt;--%>
    <%--<input type="submit" name="name" value="Search"/>--%>
<%--</form>--%>
<form action="<c:url value="/doSearchEmployee"/>" method="post" id="searchForm">
    Search in: <select name="searchIn" form="searchForm">
    <option value="name">name</option>
    <option value="surname">surname</option>
    <option value="phone">phone</option>
    <option value="department">department</option>
    <option value="leaderName">leader name</option>
</select><input type="text" name="searchValue">
    <input type="submit">
</form>
<br>

</body>
</html>
