package com.dh.clinica.service.imp;

import com.dh.clinica.dto.entrada.odontologoEntradaDTO;
import com.dh.clinica.dto.modificacion.odontologoModificacionEntradaDTO;
import com.dh.clinica.dto.salida.odontologoSalidaDTO;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    public odontologoSalidaDTO registrar(odontologoEntradaDTO odontologo) {
        Odontologo odGuardado = odontologoRepository.save(dtoEntradaAEntidad(odontologo));
        odontologoSalidaDTO odontologoSalidaDto = modelMapper.map(odGuardado, odontologoSalidaDTO.class);
        LOGGER.info("Odontologo guardado: {}", odontologoSalidaDto);
        return odontologoSalidaDto;

    }

    public void eliminar(Long id){
        if (buscarPorId(id) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontologo con id {}", id);

        }
    }

    public odontologoSalidaDTO buscarPorId(Long  id) {

        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);

        odontologoSalidaDTO odontologoSalidaDto = null;
        if (odontologoBuscado != null) {
            odontologoSalidaDto = modelMapper.map(odontologoBuscado, odontologoSalidaDTO.class);
            LOGGER.info("Odontologo encontrado: {}", odontologoSalidaDto);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return odontologoSalidaDto;
    }

    public List<odontologoSalidaDTO> listar() {
        List<odontologoSalidaDTO> odontologos = odontologoRepository.findAll().stream()
                .map(o -> modelMapper.map(o, odontologoSalidaDTO.class)).toList();

        LOGGER.info("Listado de todos los odontologos: {}", odontologos);

        return odontologos;
    }

    public odontologoSalidaDTO actualizar(odontologoModificacionEntradaDTO odontologo ) {
        Odontologo odontologoRecibido = modelMapper.map(odontologo, Odontologo.class);
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
        odontologoSalidaDTO odontologoSalidaDto = null;

        if (odontologoAActualizar != null) {

            odontologoAActualizar = odontologoRecibido;
            odontologoRepository.save(odontologoAActualizar);

            odontologoSalidaDto = modelMapper.map(odontologoAActualizar, odontologoSalidaDTO.class);

            LOGGER.warn("Odontologo actualizado: {}", odontologoSalidaDto);

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");

        }


        return odontologoSalidaDto;
    }

    private Odontologo dtoEntradaAEntidad(odontologoEntradaDTO odontologoEntradaDto) {
        return modelMapper.map(odontologoEntradaDto, Odontologo.class);
    }

}
