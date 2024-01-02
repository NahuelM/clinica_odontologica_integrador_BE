package com.dh.clinica.controller;

import com.dh.clinica.model.Odontologo;

import com.dh.clinica.dto.salida.odontologoSalidaDTO;
import com.dh.clinica.dto.entrada.odontologoEntradaDTO;
import com.dh.clinica.dto.modificacion.odontologoModificacionEntradaDTO;
import com.dh.clinica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/odontologos")
public class OdontologoController {

    private final IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }



    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<odontologoSalidaDTO> registrarOdontologo(@RequestBody odontologoEntradaDTO odontologo) {

        return new ResponseEntity<>(odontologoService.registrar(odontologo), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<odontologoSalidaDTO> buscarOdontologosPorId(@PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.buscarPorId(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<odontologoSalidaDTO> actualizarOdontologos(@RequestBody odontologoModificacionEntradaDTO odontologo) {
        return new ResponseEntity<>(odontologoService.actualizar(odontologo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarOdontologos(@PathVariable Long id) {
        odontologoService.eliminar(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<List<odontologoSalidaDTO>> listarOdontologos(){
        return new ResponseEntity<>(odontologoService.listar(), HttpStatus.OK);
    }



}
