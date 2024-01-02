package com.dh.clinica.dto.entrada;

public class domicilioEntradaDTO {
    //@NotNull(message = "La calle no puede ser nula")
    //@NotBlank(message = "Debe especificarse el nombre de la calle")
    private String calle;

    //@NotNull(message = "El numero no puede ser nulo")
    //@Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    private int numero;

    //@NotNull(message = "La localidad no puede ser nula")
    //@NotBlank(message = "Debe especificarse la localidad")
    private String localidad;

    //@NotNull(message = "La provincia no puede ser nula")
    //@NotBlank(message = "Debe especificarse el nombre de la provincia")
    private String provincia;

    public domicilioEntradaDTO() {
    }

    public domicilioEntradaDTO(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
