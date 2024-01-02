package com.dh.clinica.service;

import java.util.List;
import com.dh.clinica.dto.entrada.odontologoEntradaDTO;
import com.dh.clinica.dto.salida.odontologoSalidaDTO;
import com.dh.clinica.dto.modificacion.odontologoModificacionEntradaDTO;


public interface IOdontologoService {
    List<odontologoSalidaDTO> listar();


    odontologoSalidaDTO registrar(odontologoEntradaDTO odontologo);

    odontologoSalidaDTO buscarPorId(Long id);

    void eliminar(Long id);

    odontologoSalidaDTO actualizar(odontologoModificacionEntradaDTO odontologo);

}
