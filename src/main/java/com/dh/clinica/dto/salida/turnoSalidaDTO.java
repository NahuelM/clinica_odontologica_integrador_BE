package com.dh.clinica.dto.salida;


import java.util.Date;

public class turnoSalidaDTO {
    private Long id;
    private pacienteSalidaTurnoDTO pacienteTurnoSalidaDto;
    private odontologoSalidaTurnoDTO odontologoTurnoSalidaDto;

    private Date fechaYHora;

    public turnoSalidaDTO() {
    }

    public turnoSalidaDTO(Long id, pacienteSalidaTurnoDTO pacienteTurnoSalidaDto, odontologoSalidaTurnoDTO odontologoTurnoSalidaDto, Date fechaYHora) {
        this.id = id;
        this.pacienteTurnoSalidaDto = pacienteTurnoSalidaDto;
        this.odontologoTurnoSalidaDto = odontologoTurnoSalidaDto;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public pacienteSalidaTurnoDTO getPacienteTurnoSalidaDto() {
        return pacienteTurnoSalidaDto;
    }

    public void setPacienteTurnoSalidaDto(pacienteSalidaTurnoDTO pacienteTurnoSalidaDto) {
        this.pacienteTurnoSalidaDto = pacienteTurnoSalidaDto;
    }

    public odontologoSalidaTurnoDTO getOdontologoTurnoSalidaDto() {
        return odontologoTurnoSalidaDto;
    }

    public void setOdontologoTurnoSalidaDto(odontologoSalidaTurnoDTO odontologoTurnoSalidaDto) {
        this.odontologoTurnoSalidaDto = odontologoTurnoSalidaDto;
    }

    public Date getDate() {
        return fechaYHora;
    }

    public void setDate(Date fechaYHora) {
        this.fechaYHora = fechaYHora;
    }


    @Override
    public String toString() {
        return "Id: " + id + " - Paciente: " + pacienteTurnoSalidaDto + " - Odontologo: " + odontologoTurnoSalidaDto + " - Fecha y hora: " + fechaYHora;
    }
}
