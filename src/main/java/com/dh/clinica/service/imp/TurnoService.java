package com.dh.clinica.service.imp;

import com.dh.clinica.dto.entrada.turnoEntradaDTO;
import com.dh.clinica.dto.modificacion.turnoModificacionEntradaDTO;
import com.dh.clinica.dto.salida.turnoSalidaDTO;
import com.dh.clinica.dto.salida.odontologoSalidaDTO;
import com.dh.clinica.dto.salida.pacienteSalidaDTO;
import com.dh.clinica.dto.salida.pacienteSalidaTurnoDTO;
import com.dh.clinica.dto.salida.odontologoSalidaTurnoDTO;
import com.dh.clinica.model.Turno;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Odontologo;

import com.dh.clinica.repository.impl.TurnoRepository;
import com.dh.clinica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;


    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;

    }

    public turnoSalidaDTO registrar(turnoEntradaDTO turnoEntradaDto) {
        turnoSalidaDTO turnoSalidaDto = null;
        LOGGER.info(turnoEntradaDto.getDate().toString());
        pacienteSalidaDTO paciente = pacienteService.buscarPorId(turnoEntradaDto.getPacienteId());
        odontologoSalidaDTO odontologo = odontologoService.buscarPorId(turnoEntradaDto.getOdontologoId());

        String pacienteNoEnBdd = "El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEnBdd = "El odontologo no se encuentra en nuestra base de datos";

        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");

            } else if (paciente == null) {
                LOGGER.error(pacienteNoEnBdd);

            } else {
                LOGGER.error(odontologoNoEnBdd);

            }
        } else {
            Turno t = modelMapper.map(turnoEntradaDto, Turno.class);
            LOGGER.info(t.toString());
            Turno turnoNuevo = turnoRepository.save(t);
            turnoSalidaDto = entidadADto(turnoNuevo);

            LOGGER.info("Nuevo turno registrado con exito: {}", turnoSalidaDto);
        }

        return turnoSalidaDto;
    }

    public List<turnoSalidaDTO> listar() {
        List<turnoSalidaDTO> turnos = turnoRepository.findAll().stream()
                .map(this::entidadADto).toList();

        LOGGER.info("Listado de todos los turnos: {}", turnos);

        return turnos;
    }

    public void eliminar(Long id) {

        if (buscarPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);

        }

    }

    public void eliminarTurnosAsocOdontologo(Long id){
        List<turnoSalidaDTO> turnos = buscarPorIdOdontologo(id);
        for (turnoSalidaDTO t:turnos) {
            eliminar(t.getId());
        }
    }

    public List<turnoSalidaDTO> buscarPorIdPaciente(Long id){
        List<turnoSalidaDTO> turnos = turnoRepository.listarTurnosPorIdPaciente(id).stream()
                .map(this::entidadADto).toList();
        LOGGER.info("Listado de todos los turnos: {}", turnos);

        return turnos;
    }

    public void eliminarTurnosAsocPaciente(Long id){
        List<turnoSalidaDTO> turnos = buscarPorIdPaciente(id);
        for (turnoSalidaDTO t:turnos) {
            eliminar(t.getId());
        }
    }

    public turnoSalidaDTO modificar(turnoModificacionEntradaDTO turno) {

        Turno turnoAActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        turnoSalidaDTO turnoSalidaDto = null;

        if (turnoAActualizar != null) {

            turnoAActualizar.setPaciente(modelMapper.map(pacienteService.buscarPorId(turno.getIdPaciente()), Paciente.class));
            turnoAActualizar.setOdontologo(modelMapper.map(odontologoService.buscarPorId(turno.getIdOdontologo()), Odontologo.class));
            turnoAActualizar.setDate(turno.getDate());
            turnoRepository.save(turnoAActualizar);

            turnoSalidaDto = entidadADto(turnoAActualizar);

            LOGGER.warn("Turno actualizado: {}", turnoSalidaDto);

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el turno no se encuentra registrado");

        }


        return turnoSalidaDto;
    }

    public turnoSalidaDTO buscarPorId(Long id) {

        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);

        turnoSalidaDTO turnoSalidaDto = null;
        if (turnoBuscado != null) {
            turnoSalidaDto = entidadADto(turnoBuscado);
            LOGGER.info("Turno encontrado: {}", turnoSalidaDto);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return turnoSalidaDto;
    }

    public List<turnoSalidaDTO> buscarPorIdOdontologo(Long id){
        List<turnoSalidaDTO> turnos = turnoRepository.listarTurnosPorIdOdontologo(id).stream()
                .map(this::entidadADto).toList();
        LOGGER.info("Listado de todos los turnos: {}", turnos);

        return turnos;
    }
    private pacienteSalidaTurnoDTO pacienteSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(pacienteService.buscarPorId(id), pacienteSalidaTurnoDTO.class);
    }

    private odontologoSalidaTurnoDTO odontologoSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(odontologoService.buscarPorId(id), odontologoSalidaTurnoDTO.class);
    }

    private turnoSalidaDTO entidadADto(Turno turno) {
        turnoSalidaDTO turnoSalidaDto = modelMapper.map(turno, turnoSalidaDTO.class);
        turnoSalidaDto.setPacienteTurnoSalidaDto(pacienteSalidaDtoASalidaTurnoDto(turno.getPaciente().getId()));
        turnoSalidaDto.setOdontologoTurnoSalidaDto(odontologoSalidaDtoASalidaTurnoDto(turno.getOdontologo().getId()));
        return turnoSalidaDto;
    }
}
