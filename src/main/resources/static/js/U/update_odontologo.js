$(document).ready(function(){
    $("#update_odontologo_form").submit(function(evt) {
        evt.preventDefault();
        try {
            let odontologoId = $("#odontologo_id").val();
            
        let formData = {
            id: $("#odontologo_id").val(),
            nombre : $("#nombre").val(),
            apellido :  $("#apellido").val(),
            matricula: $("#matricula").val(),
        }
            
            $.ajax({
                url: '/odontologos',
                type: 'PUT',
                contentType : "application/json",
                data: JSON.stringify(formData),
                dataType : 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let odontologo = response;
        
                    let successAlert = '<div class="alert alert-success alert-dismissible">' + 
                                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                            '<strong> odontologo actualizado </strong></div>'

                 
                    $("#tr_" + odontologoId + " td.td_first_name").text(odontologo.nombre.toUpperCase());
                    $("#tr_" + odontologoId + " td.td_last_name").text(odontologo.apellido.toUpperCase());
                    $("#tr_" + odontologoId + " td.td_matricula").text(odontologo.matricula);

                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({"display": "block"});
                },

                error: function (response) {
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
        let odontologoId = id_of_button.split("_")[2];
  
        $.ajax({
            url: '/odontologos/' + odontologoId,
            type: 'GET',
            success: function(response) {
                let odontologo = response;                
                $("#odontologo_id").val(odontologo.id);
                $("#nombre").val(odontologo.nombre);
                $("#apellido").val(odontologo.apellido);
                $("#matricula").val(odontologo.matricula);
                $("#div_odontologo_updating").css({"display": "block"});
            },
            error: function(error){
                console.log(error);
                alert("Error -> " + error);
            }
        });        
    });

    $(document).on("click", "table button.eliminar", function(){
            let id_of_button = (event.srcElement.id);
            let odontologoId = id_of_button.split("_")[2];
            //No es necesario verificar si id existe en base ya que se toma directamente de la tabla, y esta se genera solo con elementos pertenecientes a la bd
            //Primero borramos todos los turnos asociados al odontologo con odontologo_id

            let eliminarTurnosPromise = $.ajax({
                url:'/turnos/odontologo/'+odontologoId,
                type: 'DELETE',
                success: function(response){},
                error: function(error){
                    console.log(error);
                }
            });


            $.when(eliminarTurnosPromise).done(function(response) {
                alert("Turnos asociados al odontólogo eliminados");
                $.ajax({
                    url: '/admin/odontologos/' + odontologoId,
                    type: 'DELETE',
                    success: function(response) {
                        alert("Exito odontologo con Id: " + odontologoId + " eliminado");
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