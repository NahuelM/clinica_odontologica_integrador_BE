package com.dh.clinica.dto.salida;

import java.time.LocalDate;
import java.util.Date;

public class pacienteSalidaDTO {
    private Long id;
    private String nombre;

    private String apellido;
    private String dni;

    private Date fechaIngreso;
    private domicilioSalidaDTO domicilio;

    public pacienteSalidaDTO() {
    }

    public pacienteSalidaDTO(Long id, String nombre, String apellido, String dni, Date fechaIngreso, domicilioSalidaDTO domicilio) {
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

    public domicilioSalidaDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(domicilioSalidaDTO domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - DNI: " + dni + " - Fechas de ingreso: " + fechaIngreso + " - Domicilio: " + domicilio;
    }
}
