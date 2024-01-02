$(document).ready(function(){
    $("#update_paciente_form").submit(function(evt) {
        evt.preventDefault();
        try {
            let pacienteId = $("#paciente_id").val();

        let formData = {
            id: $("#paciente_id").val(),
            nombre : $("#nombre").val(),
            apellido :  $("#apellido").val(),
            dni : $("#dni").val(),
            fechaIngreso : $("#fechaIngreso").val(),
            calle: $("#calle").val(),
            numero: $("#numero").val(),
            localidad: $("#localidad").val(),
            provincia : $("#provincia").val()
        }

            $.ajax({
                url: '/pacientes',
                type: 'PUT',
                contentType : "application/json",
                data: JSON.stringify(formData),
                dataType : 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let paciente = response;
                    //console.log(paciente.fechaIngreso);
                    //console.log(data.fechaIngreso);
                    let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                            '<strong> paciente actualizado </strong></div>';
                    //let fechaIngresoAux = new Date(paciente.fechaIngreso);

                    $("#tr_" + pacienteId + " td.td_first_name").text(paciente.nombre.toUpperCase());
                    $("#tr_" + pacienteId + " td.td_last_name").text(paciente.apellido.toUpperCase());
                    $("#tr_" + pacienteId + " td.td_dni").text(paciente.dni);
                    $("#tr_" + pacienteId + " td.td_fechaIngreso").text(paciente.fechaIngreso);
                    //domicilio
                    $("#tr_" + pacienteId + " td.td_calle").text(paciente.calle);
                    $("#tr_" + pacienteId + " td.td_numero").text(paciente.numero);
                    $("#tr_" + pacienteId + " td.td_localidad").text(paciente.localidad);
                    $("#tr_" + pacienteId + " td.td_provincia").text(paciente.provincia);

                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({"display": "block"});
                },

                error: function (response) {
                    console.log(response)
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                        '<strong> Error </strong></div>';

                    $("#response").empty();
                    $("#response").append(errorAlert);
                    $("#response").css({"display": "block"});
                }
            });
        } catch(error){
            console.log(error);
            alert(error);
        }
    });

    $(document).on("click", "table button.btn_id", function(){
        let id_of_button = (event.srcElement.id);
        let pacienteId = id_of_button.split("_")[2];

        $.ajax({
            url: '/pacientes/' + pacienteId,
            type: 'GET',
            success: function(response) {
                let paciente = response;
                $("#paciente_id").val(paciente.id);
                $("#nombre").val(paciente.nombre);
                $("#apellido").val(paciente.apellido);
                $("#dni").val(paciente.dni);
                $("#fechaIngreso").val(paciente.fechaIngreso.split("T")[0]);
                $("#calle").val(paciente.domicilio.calle);
                $("#numero").val(paciente.domicilio.numero);
                $("#localidad").val(paciente.domicilio.localidad);
                $("#provincia").val(paciente.domicilio.provincia);
                $("#div_paciente_updating").css({"display": "block"});
            },
            error: function(error){
                console.log(error);
                alert("Error -> " + error);
            }
        });
    });


    $(document).on("click", "table button.eliminar", function(){
            let id_of_button = (event.srcElement.id);
            let pacienteId = id_of_button.split("_")[2];
            //No es necesario verificar si id existe en base ya que se toma directamente de la tabla, y esta se genera solo con elementos pertenecientes a la bd
            //Primero borramos todos los turnos asociados al paciente con paciente_id


            let eliminarTurnosPromise = $.ajax({
                url:'/turnos/paciente/'+pacienteId,
                type: 'DELETE',
                success: function(response){},
                error: function(error){
                    console.log(error);
                }
            });

            $.when(eliminarTurnosPromise).done(function(response) {
                $.ajax({
                    url: '/pacientes/' + pacienteId,
                    type: 'DELETE',
                    success: function(response) {
                        alert("Exito paciente con Id: " + pacienteId + " eliminado");
                        //recargar tabla
                        location.reload();
                    },
                    error: function(error){
                        console.log(error);
                        alert("Error -> " + error);
                    }
                });
            });
    });

});