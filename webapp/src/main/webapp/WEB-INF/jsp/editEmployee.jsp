<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
    <link href="<c:url value="/css/employees.css"/>" rel="stylesheet" type="text/css">
    <jsp:include page="header.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/js/jquery/jquery.validate.js"/>"></script>
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

});
  $( function() {
    $( document ).tooltip();
  });

    </script>
    <script>
        $(document).ready(function(){
            var wr         = $(".divTableRow"); //Fields wrapper
            $("#btn1").click(function(){

                var regex = /^\+?(0|[1-9]\d*)$/;

                if ($("#addPhone").val().length != 10 | !regex.test($("#addPhone").val()))
                {
                    alert( 'Проверьте, телефон должен состоять из 10 цифр' );
                }
                else {
                    $("#add").before('' +
                        '<div class="divTableRow">' +
                        '<div class="divTableCell">' + $("#addPhone").val() + '</div>' +
                        '<div class="divTableCell">' + $("#addType").val() + '</div>' +
                        '<div class="divTableCell"><a href="#" class="remove_field">Remove</a></div>' +
                        '</div>');
                }
            });
            // $("#rem").click(function(){
            //     $(this).parent('div').remove();
            // })
            $(document).on("click",".remove_field", function(e){ //user click on remove text
                e.preventDefault();
                // $("#del").remove();
                $(this).parent('div').parent('div').remove();
            })


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


</head>
<body>

<form action="<c:url value="/employee/update"/>" id="updateEmployeeForm" method="post" enctype="multipart/form-data">
    Name: <input type="text" id="name" name="name" value="${employee.name}"/><br/>
    Surname: <input type="text" id="surname" name="surname" value="${employee.surname}"/><br/>
    Phone: <input type="text" id="phone" name="phone" value="${employee.phone}"/><br/>
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

    <%--<div id="project-label">--%>
    <%--Chief id:</div>--%>

    Chief: <input id="chief" name="chief" title="Start to enter name/surname to select chief.">
    <input id="chiefId" name="chiefId" value="0" type="hidden">
    <br/>
    Upload photo: <input type="file" id="photo" name="photo"/>


    Phones:
    <br/>
    <br/>

    <div class="divTable">
        <div class="divTableBody">
            <div class="divTableRow">
                <div class="divTableCell">Number (+7)</div>
                <div class="divTableCell">Type</div>
                <div class="divTableCell"></div>
            </div>
            <c:forEach var="phone" items="${phones}" varStatus="status">
                <div class="divTableRow">
                    <div class="divTableCell">${phone.number}</div>
                    <div class="divTableCell">
                            ${phone.type}
                    </div>
                    <div class="divTableCell"><a href="#" class="remove_field">Remove</a></div>
                </div>
            </c:forEach>
            <div id="add" class="divTableRowAdd">
                <div class="divTableCell">
                    <input type="text" id="addPhone" name="addPhone" length="10" required value=""/>
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


    <input type="submit" name="update" id="update" value="Update"/>
</form>

<form class="cmxform" id="commentForm" method="get" action="">
    <fieldset>
        <legend>Please provide your name, email address (won't be published) and a comment</legend>
        <p>
            <label for="cname">Name (required, at least 2 characters)</label>
            <input id="cname" name="name" length="10" type="text" required>
        </p>
        <p>
            <label for="cemail">E-Mail (required)</label>
            <input id="cemail" type="email" name="email" required>
        </p>
        <p>
            <label for="curl">URL (optional)</label>
            <input id="curl" type="url" name="url">
        </p>
        <p>
            <label for="ccomment">Your comment (required)</label>
            <textarea id="ccomment" name="comment" required></textarea>
        </p>
        <p>
            <input class="submit" type="submit" value="Submit">
        </p>
    </fieldset>
</form>
</body>
</html>
