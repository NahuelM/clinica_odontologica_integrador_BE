$(document).ready(function(){
    $("#update_turno_form").submit(function(evt) {
        evt.preventDefault();
        try {
            let turnoId = $("#turno_id").val();

        let formData = {
            id: $("#turno_id").val(),
            idPaciente: $("#paciente_").val().split(" ")[0],
            idOdontologo: $("#odontologo_").val().split(" ")[0],
            date: $("#fechaTurno").val().split("T")[0]
        }

            $.ajax({
                url: '/turnos',
                type: 'PUT',
                contentType : "application/json",
                data: JSON.stringify(formData),
                dataType : 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let turno = response;
                    console.log(turno);
                    let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                            '<strong> paciente actualizado </strong></div>';

                    let odonSalAux = turno.odontologoTurnoSalidaDto;
                    let pacSalAux = turno.pacienteTurnoSalidaDto
                    $("#tr_" + turnoId + " td.td_id").text(turno.id);
                    $("#tr_" + turnoId + " td.td_odontologo").text(odonSalAux.id +" " + odonSalAux.nombre + " " + odonSalAux.apellido);
                    $("#tr_" + turnoId + " td.td_paciente").text(odonSalAux.id +" " + odonSalAux.nombre + " " + odonSalAux.apellido);
                    $("#tr_" + turnoId + " td.td_fechaYHora").text(turno.date);


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
        let turnoId = id_of_button.split("_")[2];

        $.ajax({
            url: '/turnos/' + turnoId,
            type: 'GET',
            success: function(response) {
                let turno = response;
                let salidaOdonDto = turno.odontologoTurnoSalidaDto;
                let salidaPacDto = turno.pacienteTurnoSalidaDto;

                let fechaSplit = turno.date.split("T")[0].split("-");
                let fechaFormateada = fechaSplit[1]+"-"+fechaSplit[2]+"-"+fechaSplit[0];

                $("#turno_id").val(turno.id);
                $("#paciente_").val(salidaPacDto.id + " " +salidaPacDto.nombre + " " + salidaPacDto.apellido);
                $("#odontologo_").val(salidaOdonDto.id + " " +salidaOdonDto.nombre + " " + salidaOdonDto.apellido);
                $("#fechaTurno").val("02/02/2022");
                $("#div_turno_updating").css({"display": "block"});
            },
            error: function(error){
                console.log(error);
                alert("Error -> " + error);
            }
        });
    });


    $(document).on("click", "table button.eliminar", function(){
            let id_of_button = (event.srcElement.id);
            let turnoId = id_of_button.split("_")[2];
            //No es necesario verificar si id existe en base ya que se toma directamente de la tabla, y esta se genera solo con elementos pertenecientes a la bd
            //Primero borramos todos los turnos asociados al paciente con paciente_id


            let eliminarTurnosPromise = $.ajax({
                url:'/turnos/'+turnoId,
                type: 'DELETE',
                success: function(response){location.reload();},
                error: function(error){
                    console.log(error);
                }
            });

            /*$.when(eliminarTurnosPromise).done(function(response) {
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
            });*/
    });

});