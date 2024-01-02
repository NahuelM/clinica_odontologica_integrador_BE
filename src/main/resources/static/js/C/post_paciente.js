$(document).ready(function() {
    $("#add_new_paciente").submit(function(evt) {
        evt.preventDefault();

        let formData = {
            nombre : $("#nombre").val(),
            apellido :  $("#apellido").val(),
            dni:$("#dni").val(),
            fechaIngreso:$("#fechaIngreso").val().split("T")[0],
            domicilio:{
                    calle:$("#calle").val(),
                    numero:$("#numero").val(),
                    localidad:$("#localidad").val(),
                    provincia:$("#provincia").val()
                }
        }

        $.ajax({
            url: '/pacientes',
            type: 'POST',
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType : 'json',
            async: false,
            cache: false,
            success: function (response) {
               let paciente = response;
               console.log(response);
               console.log(JSON.stringify(formData));
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> paciente agregado </div>'
                $("#response").append(successAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                $("#response").append(errorAlert);
                $("#response").css({"display": "block"});
                console.log(response);
                console.log(JSON.stringify(formData));
                resetUploadForm();
            }
        });
    });

    function resetUploadForm(){
        $("#nombre").val("");
        $("#apellido").val("");
        $("#dni").val("");
        $("#fechaIngreso").val("");

        $("#calle").val("");
        $("#numero").val("");
        $("#localidad").val("");
        $("#provincia").val("");

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/pacientes.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});