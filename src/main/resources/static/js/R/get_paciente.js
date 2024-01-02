$(document).ready(function(){
    (function(){
        $.ajax({
            type : "GET",
            url : "/pacientes",
            success: function(response){
              $.each(response, (i, paciente) => {

                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' +
                                            paciente.id +
                                            '</button>';

                let eliminar_button = '<button' +
                                            ' id=' + '\"' + 'elim_id_' + paciente.id + '\"' +
                                            ' type="button" class="btn btn-info eliminar">' +
                                            'Eliminar' +
                                            ' </button>';

                let tr_id = 'tr_' + paciente.id;
                let pacienteRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + get_More_Info_Btn + '</td>' +
                          '<td class=\"td_first_name\">' + paciente.nombre.toUpperCase() + '</td>' +
                          '<td class=\"td_last_name\">' + paciente.apellido.toUpperCase() + '</td>' +
                          '<td class=\"td_dni\">' + paciente.dni+ '</td>' +
                          '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso.split("T")[0] + '</td>' +
                          '<td class=\"td_Calle\">' + paciente.domicilio.calle +'</td>' +
                          '<td class=\"td_Numero\">' + paciente.domicilio.numero +'</td>' +
                          '<td class=\"td_Localidad\">' + paciente.domicilio.localidad +'</td>' +
                          '<td class=\"td_Provincia\">' + paciente.domicilio.provincia +'</td>' +
                          '<td>' + eliminar_button + '</td>'
                          '</tr>';
                $('#pacienteTable tbody').append(pacienteRow);
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
        if (pathname == "/pacientes.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});