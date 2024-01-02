$(document).ready(function() {
    $(".dropdown-menu.odontologo").on("click", ".opcion", function () {
        $("#dropdownMenuButton").text($(this).text());
        let id = $("#dropdownMenuButton").text();
        let container = $(".container-fluid");
        container.empty();
        $.ajax({
            type : "GET",
            url : "/turnos/odontologo/"+id.split(" ")[0],
            success: function(response){
                $.each(response, (i, turno) => {
                    container.append("<div>"+turno.date.split("T")[0]+"</div>");
                });
            },
            error:function(error){
                console.log(error);
            }
        });
    });

    $(".dropdown-menu.paciente").on("click", ".opcion", function () {
        $("#dropdownMenuButtonP").text($(this).text());
    });

    //Cargo el combobox de odontologos
    loadOdontologoCombobox();
    //Cargo el combobox de pacientes
    loadPacienteCombobox();


    $("#add_new_turno").submit(function(evt) {
        evt.preventDefault();
        let odontologoSelec = $("#dropdownMenuButton").text();
        let pacienteSelec = $("#dropdownMenuButtonP").text();
        const regex = /^\d\s.*$/;
        console.log(odontologoSelec + " " + pacienteSelec);
        if(regex.test(odontologoSelec) && regex.test(pacienteSelec)){
            let formData = {
                pacienteId:1,
                odontologoId:parseInt(odontologoSelec.split(" ")[0]),//me quedo con el id del odontologo seleccionado
                date:$("#fechaTurno").val().split("T")[0]
            };
            $.ajax({
                url: '/turnos',
                type: 'POST',
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
                        '<strong></strong> turno asignado </div>'
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
                    console.log(formData)
                    resetUploadForm();
                }
            });
        }
        else
            alert("INFO", "Seleccione elementos de las listas desplegables: Seleccionar paciente y Seleccionar odontologo");
    });

    function resetUploadForm(){
        console.log($(".dropdown-menu.paciente"));
        $(".dropdown-menu.paciente").val("Seleccionar paciente");
        $(".dropdown-menu.odontologo").val("Seleccionar odontologo");
        $('#fechaTurno').val("");

    }

    function loadOdontologoCombobox(){
        $.ajax({
            type : "GET",
            url : "/odontologos",
            success: function(response){
              $.each(response, (i, odontologo) => {
                console.log(odontologo);
                let nuevoElemento = "<a class=\"dropdown-item dropdown-item opcion\" href='#'>"+odontologo.id + " " + odontologo.nombre+" "+ odontologo.apellido+"</a>"
                console.log(nuevoElemento)
                $(".dropdown-menu.odontologo").append(nuevoElemento);
              });
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
    }

    function loadPacienteCombobox(){
        $.ajax({
            type : "GET",
            url : "/pacientes",
            success: function(response){
              $.each(response, (i, paciente) => {
                console.log(paciente);
                let nuevoElemento = "<a class=\"dropdown-item dropdown-item opcion\" href='#'>"+paciente.id + " " + paciente.nombre+" "+ paciente.apellido+"</a>"
                console.log(nuevoElemento)
                $(".dropdown-menu.paciente").append(nuevoElemento);
              });
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
    }
});