<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="<c:url value="/css/body.css"/>" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( "#menu" ).menu();
    } );
</script>

<script>
    $(function() {
        $( "#tags" ).autocomplete({
            source: function( request, response ) {
                jQuery.ajax({
                    url: '<c:url value="/employee/search2"/>',
                    data: {
                     q: request.term
    },
    success: function( data ) {
                        response( $.map(data, function (v) {
                            return {label: v.name, value: v.id};
    }));
    }

                });
            }
        });
        }
    );
</script>

<style>
    .ui-menu { width: 150px; }
</style>