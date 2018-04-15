<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
    <jsp:include page="header.jsp"/>
    <script>
  $( function() {

  var projects = [
    <c:forEach var="chief" items="${employees}" varStatus="status">
  {
    value: "${chief.id}",
    label: "${chief.surname}&nbsp;${chief.name}"
  },
    </c:forEach>
    {
    value: "0",
    label: "Empty"
    }
  ];

  $("#chief").autocomplete({
      minLength: 0,
      source: projects,
      focus: function(event, ui) {
        $("#project").val(ui.item.label);
        return false;
      },
      select: function(event, ui) {
        $("#chief").val(ui.item.label);
        $("#chiefId").val(ui.item.value);
        <%--$("#project-description").html(ui.item.desc);--%>
        return false;
      }
    })
    .autocomplete("instance")._renderItem = function(ul, item) {
      return $("<li>")
        .append("<a>" + item.label + "<br>" + "</a>")
         <%--item.desc + --%>

        .appendTo(ul);
    };


    <%--var availableTags = [--%>

    <%--<c:forEach var="chief" items="${employees}" varStatus="status">--%>
        <%--[${chief.id},"${chief.surname}"],--%>

    <%--</c:forEach>--%>
    <%--[0, "Empty"]--%>

    <%--];--%>

});



  $( function() {
    $( document ).tooltip();
  } );
    </script>

    <style>
        label {
            display: inline-block;
            width: 5em;
        }

        #project-label {
            display: block;
            font-weight: bold;
            margin-bottom: 1em;
        }
        #project-icon {
            float: left;
            height: 32px;
            width: 32px;
        }
        #project-description {
            margin: 0;
        }
    </style>


</head>
<body>

<form action="<c:url value="/employee/update"/>" id="updateEmployeeForm" method="post" enctype="multipart/form-data">
    Name: <input type="text" id="name" name="name" value="${employee.name}"/>
    Surname: <input type="text" id="surname" name="surname" value="${employee.surname}"/>
    Phone: <input type="text" id="phone" name="phone" value="${employee.phone}"/>
    <%--Chief: <input type="text" id="chiefId" name="chiefId" value="${employee.chiefId}"/>--%>
    <%--Chief: <select name="chiefId" form="updateEmployeeForm">--%>
    <%--<c:forEach var="chief" items="${employees}" varStatus="status">--%>
    <%--<option value="${chief.id}">${chief.surname} &nbsp; ${chief.name}</option>--%>
    <%--</c:forEach>--%>
    <%--<option value="0">Empty</option>--%>
    <%--</select>--%>
    <%--<div class="ui-widget">--%>
        <%--<label for="tags">Chief: </label>--%>
        <%--<input type="text" id="tags" name="tags">--%>
    <%--</div>--%>

    <%--<input type="text" name="chiefId" value="" class="employeeAutocomplete" />--%>

    <br/>
    Upload photo: <input type="file" id="photo" name="photo"/>
    <input type="hidden" id="id" name="id" value="${employee.id}">
    <div id="project-label">
        Select Chief id:</div>
    <input id="chief" name="chief" title="Start to enter name/surname to select chief.">
    <input id="chiefId" name="chiefId" value="0" type="hidden">

    <input type="submit" name="update" id="update" value="Update"/>
</form>
</body>
</html>
