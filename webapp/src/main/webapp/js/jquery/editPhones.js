var phoneRowCount = 0;
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
                '<div class="divTableCell">' + $("#addPhone").val() +
                '<input type="hidden" id="phoneNumberAdded"'+phoneRowCount+' name="phoneNumber" value="'+ $("#addPhone").val() + '">\n' +
                '</div>' +
                '<div class="divTableCell">' + $("#addType").val() +
                '<input type="hidden" id="phoneTypeAdded"'+phoneRowCount+' name="phoneType" value="'+ $("#addType").val() + '">\n' +
                '</div>' +
                '<div class="divTableCell"><a href="#" class="remove_field">Remove</a></div>' +
                '</div>');
            phoneRowCount++;
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