package com.dh.clinica.controller;

import com.dh.clinica.model.Turno;
import  com.dh.clinica.dto.entrada.turnoEntradaDTO;
import  com.dh.clinica.dto.salida.turnoSalidaDTO;
import  com.dh.clinica.dto.modificacion.turnoModificacionEntradaDTO;
import com.dh.clinica.service.imp.OdontologoService;
import com.dh.clinica.service.imp.PacienteService;
import com.dh.clinica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {


    private ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<turnoSalidaDTO> registrarTurno(@RequestBody turnoEntradaDTO turno) {
        return new ResponseEntity<>(turnoService.registrar(turno), HttpStatus.CREATED);


    }

    @GetMapping
    public ResponseEntity<List<turnoSalidaDTO>> listar() {
        return new ResponseEntity<>(turnoService.listar(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminar(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<turnoSalidaDTO> actualizarTurno(@RequestBody turnoModificacionEntradaDTO turno) {
        return new ResponseEntity<>(turnoService.modificar(turno), HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<turnoSalidaDTO> obtenerTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/odontologo/{id}")
    public ResponseEntity<List<turnoSalidaDTO>> obtenerTurnoPorIdOdontologo(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarPorIdOdontologo(id), HttpStatus.OK);
    }

    @DeleteMapping("/odontologo/{id}")
    public ResponseEntity<String> eliminarTurnosOdontologo(@PathVariable Long id) {
        turnoService.eliminarTurnosAsocOdontologo(id);
        return new ResponseEntity<>("Turnos eliminados correctamente", HttpStatus.OK);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<turnoSalidaDTO>> obtenerTurnoPorIdPaciente(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarPorIdPaciente(id), HttpStatus.OK);
    }

    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<String> eliminarTurnosPaciente(@PathVariable Long id) {
        turnoService.eliminarTurnosAsocPaciente(id);
        return new ResponseEntity<>("Turnos eliminados correctamente", HttpStatus.OK);
    }
}
