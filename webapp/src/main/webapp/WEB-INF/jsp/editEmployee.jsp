<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
    <link href="<c:url value="/css/employees.css"/>" rel="stylesheet" type="text/css">
    <jsp:include page="headerEmp.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/js/jquery/jquery.validate.js"/>"></script>
    <%--<script src="<c:url value="/js/jquery/chiefAutocomplete.js"/>"></script>--%>
    <script src="<c:url value="/js/jquery/editPhones.js"/>"></script>



    <script>
    $( function() {

    var projects = [
    <c:forEach var="chief" items="${employees}" varStatus="status">
    {
    value: "${chief.id}",
    label: "${chief.surname} ${chief.name}"
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

    });
    $( function() {
    $( document ).tooltip();
    });

    </script>
    <script>
        $(function() {
            $("#update").click(function(event){
                event.preventDefault();
                if (confirm("Click OK to continue?")){
                    $("#updateEmployeeForm").submit();
                }
            });
        });
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

    <jsp:include page="/WEB-INF/jsp/header.jsp"/>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/top.jsp"/>
<form action="<c:url value="/employee/update"/>" id="updateEmployeeForm" method="post" enctype="multipart/form-data">
    Name: <input type="text" id="name" name="name" value="${employee.name}"/><br/>
    Surname: <input type="text" id="surname" name="surname" value="${employee.surname}"/><br/>
    <%--<input type="text" id="phone" name="phone" value="${employee.phone}"/><br/>--%>
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

    <input type="hidden" id="id" name="id" value="${employee.id}">

    <%--<input type="hidden" id="phoneNumber0" name="phoneNumber" value="1111111111">--%>
    <%--<input type="hidden" id="phoneNumber1" name="phoneNumber" value="2222211111">--%>
    <%--<input type="hidden" id="phoneNumber2" name="phoneNumber" value="3333311111">--%>
    <%--<input type="hidden" id="phones[0].type" name="phonetyp" value="HOME">--%>
    <%--<input type="hidden" id="phones[1].type" name="phones[1].type" value="WORK">--%>
    <%--<input type="hidden" id="phones[2].type" name="phones[2].type" value="HOME">--%>

    <div id="project-label">
    <%--Chief id:</div>--%>

    Chief: <input id="chief" name="chief" title="Start to enter name/surname to select chief.">
    <input id="chiefId" name="chiefId" value="0" type="hidden" />
    <br/>
    Upload photo: <input type="file" id="photo" name="photo"/>


    <br/>
    <br/>

    <div class="divTable">
        <div class="divTableBody">
            <div class="divTableRow">
                <div class="divTableCell">Number (+7)</div>
                <div class="divTableCell">Type</div>
                <div class="divTableCell"></div>
            </div>
            <c:set var="count" value="0" scope="page" />
            <input type="hidden" id="phoneNumber" name="phoneNumber" value="null" />
            <input type="hidden" id="phoneType" name="phoneType" value="HOME"/>

            <c:forEach var="phone" items="${employee.phones}" varStatus="status">
                <div class="divTableRow">
                    <div class="divTableCell">
                            ${phone.number}
                                <input type="hidden" id="phoneNumber<c:set var="count" value="${count + 1}" scope="page"/>" name="phoneNumber" value="${phone.number}">

                    </div>
                    <div class="divTableCell">
                            ${phone.type}
                                <input type="hidden" id="phoneType<c:set var="count" value="${count + 1}" scope="page"/>" name="phoneType" value="${phone.type}">

                    </div>
                    <div class="divTableCell"><a href="#" class="remove_field">Remove</a></div>
                </div>
            </c:forEach>
            <div id="add" class="divTableRowAdd">
                <div class="divTableCell">
                    <input type="text" id="addPhone" name="addPhone" length="10"/>
                    <%--<p>--%>
                        <%--<label for="cname">Name (required, at least 2 characters)</label>--%>
                        <%--<input id="cname" name="name" minlength="2" type="text" required>--%>
                    <%--</p>--%>


                </div>

                <div class="divTableCell">
                    <select name="addType" id="addType" form="updateEmployeeForm">
                        <option value="HOME">HOME</option>
                        <option value="WORK">WORK</option>
                    </select>

                </div>
                <div id="btn1" class="divTableCell"><a href="#">Add</a></div>
            </div>

        </div>
    </div>

    <br/>
    <%--<div class="input_fields_wrap">--%>
    <%--</div>--%>
    <%--<div>--%>
        <%--<button class="add_field_button">Add More Fields</button>--%>
        <%--<div><input type="text" name="mytext[]"></div>--%>
    <%--</div>--%>


    <input type="submit" name="update" id="update" value="Save changes"/>
</form>

<%--<form class="cmxform" id="commentForm" method="get" action="">--%>
    <%--<fieldset>--%>
        <%--<legend>Please provide your name, email address (won't be published) and a comment</legend>--%>
        <%--<p>--%>
            <%--<label for="cname">Name (required, at least 2 characters)</label>--%>
            <%--<input id="cname" name="name" length="10" type="text" required>--%>
        <%--</p>--%>
        <%--<p>--%>
            <%--<label for="cemail">E-Mail (required)</label>--%>
            <%--<input id="cemail" type="email" name="email" required>--%>
        <%--</p>--%>
        <%--<p>--%>
            <%--<label for="curl">URL (optional)</label>--%>
            <%--<input id="curl" type="url" name="url">--%>
        <%--</p>--%>
        <%--<p>--%>
            <%--<label for="ccomment">Your comment (required)</label>--%>
            <%--<textarea id="ccomment" name="comment" required></textarea>--%>
        <%--</p>--%>
        <%--<p>--%>
            <%--<input class="submit" type="submit" value="Submit">--%>
        <%--</p>--%>
    <%--</fieldset>--%>
<%--</form>--%>
<jsp:include page="/WEB-INF/jsp/bottom.jsp"/>
</body>
</html>
