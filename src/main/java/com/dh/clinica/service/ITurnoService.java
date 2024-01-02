package com.dh.clinica.service;

import java.util.List;
import com.dh.clinica.dto.entrada.turnoEntradaDTO;
import com.dh.clinica.dto.salida.turnoSalidaDTO;
import com.dh.clinica.dto.modificacion.turnoModificacionEntradaDTO;

public interface ITurnoService {
    turnoSalidaDTO registrar(turnoEntradaDTO turno) ;

    List<turnoSalidaDTO> listar();

    turnoSalidaDTO buscarPorId(Long id);

    void eliminar(Long id);

    turnoSalidaDTO modificar(turnoModificacionEntradaDTO turno);

    List<turnoSalidaDTO> buscarPorIdOdontologo(Long id);

    void eliminarTurnosAsocOdontologo(Long id);

    List<turnoSalidaDTO> buscarPorIdPaciente(Long id);

    void eliminarTurnosAsocPaciente(Long id);
}
