package com.dh.clinica.service.imp;


import com.dh.clinica.dto.entrada.pacienteEntradaDTO;
import com.dh.clinica.dto.modificacion.pacienteModificacionEntradaDTO;
import com.dh.clinica.dto.salida.pacienteSalidaDTO;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.repository.impl.PacienteRepository;
import com.dh.clinica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }


    public pacienteSalidaDTO registrar(pacienteEntradaDTO  p) {
        Paciente pacGuardado = pacienteRepository.save(dtoEntradaAEntidad(p));
        pacienteSalidaDTO pacienteSalidaDto = entidadADtoSalida(pacGuardado);
        LOGGER.info("Paciente guardado: {}", pacienteSalidaDto);
        return pacienteSalidaDto;
    }

    public pacienteSalidaDTO buscarPorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);

        pacienteSalidaDTO pacienteSalidaDto = null;
        if (pacienteBuscado != null) {
            pacienteSalidaDto = entidadADtoSalida(pacienteBuscado);
            LOGGER.info("Paciente encontrado: {}", pacienteSalidaDto);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return pacienteSalidaDto;
    }

    public List<pacienteSalidaDTO> listar() {
        List<pacienteSalidaDTO> pacientes = pacienteRepository.findAll().stream()
                .map(this::entidadADtoSalida).toList();

        LOGGER.info("Listado de todos los pacientes: {}", pacientes);

        return pacientes;
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    public pacienteSalidaDTO modificar(pacienteModificacionEntradaDTO p)
    {
        Paciente pacienteRecibido = dtoModificadoAEntidad(p);
        Paciente pacienteAActualizar = pacienteRepository.findById(p.getId()).orElse(null);
        pacienteSalidaDTO pacienteSalidaDto = null;

        if (pacienteAActualizar != null) {

            pacienteAActualizar = pacienteRecibido;
            pacienteRepository.save(pacienteAActualizar);

            pacienteSalidaDto = entidadADtoSalida(pacienteAActualizar);

            LOGGER.warn("Paciente actualizado: {}", pacienteSalidaDto);

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");

        }


        return pacienteSalidaDto;
    }

    private void configureMapping() {
        modelMapper.typeMap(pacienteEntradaDTO.class, Paciente.class)
                .addMappings(mapper -> mapper.map(pacienteEntradaDTO::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, pacienteSalidaDTO.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, pacienteSalidaDTO::setDomicilio));
        modelMapper.typeMap(pacienteModificacionEntradaDTO.class, Paciente.class)
                .addMappings(mapper -> mapper.map(pacienteModificacionEntradaDTO::getDomicilio, Paciente::setDomicilio));

    }

    private Paciente dtoEntradaAEntidad(pacienteEntradaDTO pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

    private pacienteSalidaDTO entidadADtoSalida(Paciente paciente) {
        return modelMapper.map(paciente, pacienteSalidaDTO.class);
    }

    public Paciente dtoModificadoAEntidad(pacienteModificacionEntradaDTO pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }
}
