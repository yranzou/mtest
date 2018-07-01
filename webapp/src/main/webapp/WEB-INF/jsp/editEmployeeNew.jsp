<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/css/employeePage.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/strippedtable.css"/>" rel="stylesheet" type="text/css">

    <title>Edit employee</title>
    <link href="<c:url value="/css/employees.css"/>" rel="stylesheet" type="text/css">
    <jsp:include page="headerEmp.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/js/jquery/jquery.validate.js"/>"></script>
    <script src="<c:url value="/js/jquery/chiefAutocomplete.js"/>"></script>
    <script src="<c:url value="/js/jquery/editPhones.js"/>"></script>
    <script>
        $(function () {
            $("#update").click(function (event) {
                event.preventDefault();
                if (confirm("Click OK to continue?")) {
                    $("#updateEmployeeForm").submit();
                }
            });
        });
    </script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
    <script>
        $(function () {

            var chiefs = [
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
                source: chiefs,
                focus: function (event, ui) {
                    $("#chief").val(ui.item.label);
                    return false;
                },
                select: function (event, ui) {
                    $("#chief").val(ui.item.label);
                    $("#chiefId").val(ui.item.value);
                    <%--$("#project-description").html(ui.item.desc);--%>
                    return false;
                }
            })
                .autocomplete("instance")._renderItem = function (ul, item) {
                return $("<li>")
                    .append("<a>" + item.label + "<br>" + "</a>")
                    <%--item.desc + --%>

                    .appendTo(ul);
            };

        });
        $(function () {
            $(document).tooltip();
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
<br/>
<form action="<c:url value="/employee/update"/>" id="updateEmployeeForm" method="post" enctype="multipart/form-data">
    <div class="card">
        <div class="divTableX">
            <div class="divTableBodyX">
                <div class="divTableRowX">
                    <div class="divTableCellX">
                        <div class="divTableX">
                            <div class="divTableBodyX">
                                <div class="divTableRowX">
                                    <div class="divTableCellX">
                                        <img src="${image}" alt="employee photo"/>Update photo: <input type="file"
                                                                                                       id="photo"
                                                                                                       name="photo"/>
                                        <h1><input type="text" id="name" name="name" value="${employee.name}"/> <input
                                                type="text" id="surname" name="surname" value="${employee.surname}"/>
                                        </h1>
                                        <input type="hidden" id="id" name="id" value="${employee.id}">
                                        <h2 style="text-align:center"></h2>
                                    </div>

                                    <div class="divTableCellX">

                                    </div>
                                </div>
                                <div class="divTableRowX">
                                    <div class="divTableCellX">
                                        <div class="divTable paleBlueRows">
                                            <div class="divTableBody">
                                                <div class="divTableRow">
                                                    <div class="divTableCell">Update birthdate</div>
                                                    <div class="divTableCell">
                                                        <input type="text" name="datepicker" id="datepicker" value="${birthDate}">
                                                    </div>
                                                </div>
                                                <div class="divTableRow">
                                                    <div class="divTableCell">Update department</div>
                                                    <div class="divTableCell">
                                                        <select name="departmentId" form="updateEmployeeForm">
                                                            <%--<option value="${department.chiefId}">${thisDepartmentChief.surname} &nbsp;${thisDepartmentChief.name}</option>--%>
                                                            <c:forEach var="department" items="${departments}" varStatus="status">
                                                                <option value="${department.id}">${department.name}</option>
                                                            </c:forEach>
                                                            <option value="0">No department</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="divTableRow">
                                                    <div class="divTableCell">Select new manager</div>
                                                    <div class="divTableCell">
                                                        <input id="chief" type="text" name="chief"
                                                               title="Start to enter name/surname to select chief."
                                                               value="${employee.chief.name} ${employee.chief.surname}">
                                                        <input id="chiefId" name="chiefId" value="${employee.chief.id}"
                                                               type="hidden"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <h3 style="text-align:center">
                                                <a href="<c:url value="/employee/delete/${employee.id}" />">Delete
                                                </a>

                                            </h3>
                                        </div>
                                    </div>
                                    <div class="divTableCellX">
                                        <div class="divTable paleBlueRows">
                                            <div class="divTableBody">
                                                <div class="divTableRow">
                                                    <div class="divTableCell">Email</div>
                                                    <div class="divTableCell">n/a</div>
                                                    <div class="divTableCell"></div>
                                                </div>
                                                <div class="divTableRow">
                                                    <div class="divTableCell">Telegramm</div>
                                                    <div class="divTableCell">n/a</div>
                                                    <div class="divTableCell"></div>
                                                </div>
                                                <c:set var="count" value="0" scope="page"/>
                                                <input type="hidden" id="phoneNumber" name="phoneNumber" value="null"/>
                                                <input type="hidden" id="phoneType" name="phoneType" value="HOME"/>
                                                <c:forEach var="phone" items="${employee.phones}" varStatus="status">
                                                    <div class="divTableRow">
                                                        <div class="divTableCell">${phone.type}
                                                            <input type="hidden"
                                                                   id="phoneType<c:set var="count" value="${count + 1}" scope="page"/>"
                                                                   name="phoneType" value="${phone.type}">
                                                        </div>
                                                        <div class="divTableCell">${phone.number}
                                                            <input type="hidden"
                                                                   id="phoneNumber<c:set var="count" value="${count + 1}" scope="page"/>"
                                                                   name="phoneNumber" value="${phone.number}">
                                                        </div>
                                                        <div class="divTableCell"><a href="#" class="remove_field">Remove</a></div>
                                                    </div>
                                                </c:forEach>

                                                <div id="add" class="divTableRowAdd">
                                                    <div class="divTableCell">
                                                        <input type="text" id="addPhone" name="addPhone" length="10"/>
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
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="divTableCellX">
                <div id="reports" class="well">
                    <h3 class="modal-header">Subordinates</h3>
                    <ul class="nav nav-list">
                        <li>
                        </li>
                        <c:if test="${!empty employee.subordinates}">
                            <c:forEach var="subordinate" items="${employee.subordinates}" varStatus="status">
                                <li>
                                    <a href="${subordinate.id}">
                                        <p class="list-item">${subordinate.name} ${subordinate.surname}<br/>
                                                <%--${subordinate.department.name}--%>
                                        </p>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <input type="submit" name="update" id="update" value="Save changes"/>
</form>

<jsp:include page="/WEB-INF/jsp/bottom.jsp"/>
</body>
</html>
