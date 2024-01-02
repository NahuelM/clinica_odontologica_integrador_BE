$(document).ready(function(){
    (function(){
        $.ajax({
            type : "GET",
            url : "/turnos",
            success: function(response){
              $.each(response, (i, turno) => {

                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' +
                                            turno.id +
                                            '</button>';

                let eliminar_button = '<button' +
                                            ' id=' + '\"' + 'elim_id_' + turno.id + '\"' +
                                            ' type="button" class="btn btn-info eliminar">' +
                                            'Eliminar' +
                                            ' </button>';

                //let tr_id = 'tr_' + paciente.id;

                let pacienteAux = turno.pacienteTurnoSalidaDto!=null?turno.pacienteTurnoSalidaDto.nombre + " " +turno.pacienteTurnoSalidaDto.apellido:"Paciente no encontrado";
                let odontologoAux = turno.odontologoTurnoSalidaDto!=null?turno.odontologoTurnoSalidaDto.nombre + " " + turno.odontologoTurnoSalidaDto.apellido :"Odontologo no encontrado";
                let turnoRow = '<tr id=\"' + turno.id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                          '<td class=\"td_paciente_\">' + pacienteAux +'</td>'+
                          '<td class=\"td_odontologo_\">' + odontologoAux +'</td>'+
                          '<td class=\"td_fechaTurno\"_>' + turno.date.split("T")[0] + '</td>' +
                          '<td>' + eliminar_button + '</td>' +
                          '</tr>';
                $('#turnoTable tbody').append(turnoRow);
              });
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
    })();

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/turnos.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});