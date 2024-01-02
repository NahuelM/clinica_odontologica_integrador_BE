package com.dh.clinica.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;
public class turnoEntradaDTO {

    //@NotNull(message = "El paciente no puede ser nulo")
    private Long pacienteId;

    //@NotNull(message = "El odontologo no puede ser nulo")
    private Long odontologoId;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    //@FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    //@NotNull(message = "Debe especificarse la fecha y hora del turno")
    private Date fechaYHora;

    public turnoEntradaDTO() {
    }

    public turnoEntradaDTO(Long pacienteId, Long odontologoId, Date fechaYHora) {
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
        this.fechaYHora = fechaYHora;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public Date getDate() {
        return fechaYHora;
    }

    public void setDate(Date fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
