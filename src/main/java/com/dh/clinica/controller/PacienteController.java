package com.dh.clinica.controller;

import com.dh.clinica.model.Paciente;
import com.dh.clinica.dto.entrada.pacienteEntradaDTO;
import com.dh.clinica.dto.salida.pacienteSalidaDTO;
import com.dh.clinica.dto.modificacion.pacienteModificacionEntradaDTO;
import com.dh.clinica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;
    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @PostMapping()
    public ResponseEntity<pacienteSalidaDTO> registrarPaciente(@RequestBody pacienteEntradaDTO paciente) {

        return new ResponseEntity<>(pacienteService.registrar(paciente), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<pacienteSalidaDTO> buscarPacientePorId(@PathVariable Long id) {
        return new ResponseEntity<>(pacienteService.buscarPorId(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<pacienteSalidaDTO> actualizarPaciente(@RequestBody pacienteModificacionEntradaDTO paciente) {
        return new ResponseEntity<>(pacienteService.modificar(paciente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<pacienteSalidaDTO>> buscarTodosPacientes(){
        return new ResponseEntity<>(pacienteService.listar(), HttpStatus.OK);
    }


}
