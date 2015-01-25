/**
 * Created by rarane on 1/25/2015.
 */

var host_name = "http://app-40959649-cddd-452d-8710-fbd8e9681cc9.cleverapps.io";

$(function() {

    $('#lbl_success' ).hide();
    $('#lbl_invalid_title' ).hide();

    $('#p_id').attr("disabled", true);
    $('#p_cost').attr("disabled", true);
    var availableTags = [];
    $.ajax({
        url: host_name + "/productids",
        success: function( data ) {
            availableTags = data;
            $( "#main_id" ).autocomplete({
                    source: availableTags,
                    select: function( event, ui) {
                        if(ui.item != null) {
                            //Getting product from db
                            $.ajax({
                                url: host_name + "/product/" + ui.item.label,
                                success: function(data) {
                                    $("#p_id" ).val(data.id);
                                    $("#p_title" ).val(data.title);
                                    $("#p_price" ).val(data.pricing.price);
                                    $("#p_cost" ).val(data.pricing.cost);
                                }
                            })
                        }
                    }
                }
            );
        }
    });

    $('#p_title').keypress(function (e) {
        if (e.which == 13) {
            updateProduct();
        }
    });

    $('#p_price').keypress(function (e) {
        if (e.which == 13) {
            updateProduct();
        }
    });

    function updateProduct() {

        var obj = new Object();
        obj.id = parseInt($("#p_id" ).val());
        obj.price  = $("#p_price" ).val();
        obj.title = $("#p_title" ).val();
        var prodData = JSON.stringify(obj);

        if(validateData(obj.price,obj.title)) {
            $.ajax({
                contentType: 'application/json',
                type: "POST",
                url: host_name + "/updateproduct",
                data: prodData,
                success: function( ) {
                    $('#lbl_success' ).show();
                    setTimeout(function(){$('#lbl_success' ).hide()},1000)
                },
                dataType: "json"
            });
        }
    }

    function validateData( price, title) {

        if(IsNumeric(price)) {
            var c = $("#p_cost" ).val()
            c = parseFloat(c);
            var p = parseFloat(price);
            if(p <= c ) {
                var msg = "Price can't be less than or equal to cost";
                $('#invalid_data' ).text(msg);
                $('#lbl_invalid_title' ).show();
                setTimeout(function(){$('#lbl_invalid_title' ).hide()},2000)
                return false;
            }
        } else {
            var msg = "Price should be numeric value";
            $('#invalid_data' ).text(msg);
            $('#lbl_invalid_title' ).show();
            setTimeout(function(){$('#lbl_invalid_title' ).hide()},2000)
            return false;
        }

        if(IsNumeric(title)) {
            var msg = "Title can't be numeric value";
            $('#invalid_data' ).text(msg);
            $('#lbl_invalid_title' ).show();
            setTimeout(function(){$('#lbl_invalid_title' ).hide()},2000)
            return false;
        }
        return true;
    }

    function IsNumeric(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

});
