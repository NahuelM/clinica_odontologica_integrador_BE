package com.dh.clinica.service;

import java.util.List;
import com.dh.clinica.dto.salida.pacienteSalidaDTO;
import com.dh.clinica.dto.entrada.pacienteEntradaDTO;
import com.dh.clinica.dto.modificacion.pacienteModificacionEntradaDTO;

public interface IPacienteService {
    List<pacienteSalidaDTO> listar();

    pacienteSalidaDTO registrar(pacienteEntradaDTO paciente);

    pacienteSalidaDTO buscarPorId(Long id);

    void eliminar(Long id);

    pacienteSalidaDTO modificar(pacienteModificacionEntradaDTO pacienteModificado);
}
