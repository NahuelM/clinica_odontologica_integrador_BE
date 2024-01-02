package com.dh.clinica.dto.modificacion;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class pacienteModificacionEntradaDTO {

    //@NotNull(message = "Debe proveerse el id del paciente que se desea modificar")
    private Long id;

    //@Size(min = 2, max = 50, message = "El nombre debe tener hasta 50 caracteres")
    //@NotNull(message = "El nombre del paciente no puede ser nulo")
    //@NotBlank(message = "Debe especificarse el nombre del paciente")
    private String nombre;

    //@Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    //@NotNull(message = "El apellido del paciente no puede ser nulo")
    //@NotBlank(message = "Debe especificarse el apellido del paciente")
    private String apellido;

    //@NotNull(message = "Debe especificarse el dni del paciente")
    //@Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    private String dni;

    //@FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    //@NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaIngreso;

    //@NotNull(message = "El domicilio del paciente no puede ser nulo")
    //@Valid
    private domicilioModificacionEntradaDTO domicilio;

    public pacienteModificacionEntradaDTO() {
    }

    public pacienteModificacionEntradaDTO(Long id, String nombre, String apellido, String dni, Date fechaIngreso, domicilioModificacionEntradaDTO domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public domicilioModificacionEntradaDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(domicilioModificacionEntradaDTO domicilio) {
        this.domicilio = domicilio;
    }
}
