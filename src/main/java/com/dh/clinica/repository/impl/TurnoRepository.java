package com.dh.clinica.repository.impl;

import com.dh.clinica.dto.entrada.turnoEntradaDTO;
import com.dh.clinica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    @Query(value = "SELECT * FROM turnos t JOIN pacientes  ON pacientes.id = t.pacientes_id  WHERE t.pacientes_id = ?1", nativeQuery = true)
    List<Turno> listarTurnosPorIdPaciente(Long Id);


    @Query(value = "SELECT * FROM turnos JOIN odontologos ON turnos.odontologo_id = odontologos.id WHERE odontologos.id = ?1", nativeQuery = true)
    List<Turno> listarTurnosPorIdOdontologo(Long Id);


}
