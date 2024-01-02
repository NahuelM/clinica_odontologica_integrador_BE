package com.dh.clinica.dto.modificacion;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class turnoModificacionEntradaDTO {
    //@NotNull(message = "Debe proveerse el id del turno que se desea modificar")
    private Long id;
    //@NotNull(message = "El paciente no puede ser nulo")
    private Long idPaciente;
    //@NotNull(message = "El odontologo no puede ser nulo")
    private Long idOdontologo;

    //@FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    //@NotNull(message = "Debe especificarse la fecha y hora del turno")
    private Date fechaYHora;

    public turnoModificacionEntradaDTO() {
    }

    public turnoModificacionEntradaDTO(Long id, Long idPaciente, Long idOdontologo, Date fechaYHora) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idOdontologo = idOdontologo;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdOdontologo() {
        return idOdontologo;
    }

    public void setIdOdontologo(Long idOdontologo) {
        this.idOdontologo = idOdontologo;
    }

    public Date getDate() {
        return fechaYHora;
    }

    public void setDate(Date fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
