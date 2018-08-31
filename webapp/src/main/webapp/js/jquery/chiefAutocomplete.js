
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
        // <%--$("#project-description").html(ui.item.desc);--%>
            return false;
        }
    })
        .autocomplete("instance")._renderItem = function(ul, item) {
        return $("<li>")
                .append("<a>" + item.label + "<br>" + "</a>")
            // <%--item.desc + --%>

    .appendTo(ul);
    };

});
$( function() {
    $( document ).tooltip();
});
