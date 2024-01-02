package com.dh.clinica;

import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import  com.dh.clinica.dto.entrada.turnoEntradaDTO;
import  com.dh.clinica.dto.entrada.pacienteEntradaDTO;
import  com.dh.clinica.dto.entrada.domicilioEntradaDTO;
import  com.dh.clinica.dto.entrada.odontologoEntradaDTO;
import  com.dh.clinica.dto.salida.pacienteSalidaDTO;
import  com.dh.clinica.dto.salida.odontologoSalidaDTO;
import  com.dh.clinica.dto.salida.turnoSalidaDTO;
import  com.dh.clinica.dto.salida.domicilioSalidaDTO;



import com.dh.clinica.service.IOdontologoService;
import com.dh.clinica.service.IPacienteService;
import com.dh.clinica.service.ITurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTests {

    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    private ITurnoService turnoService;

    public void cargarDataSet() {
        domicilioEntradaDTO domicilio = new domicilioEntradaDTO("Av Santa fe", 444, "CABA", "Buenos Aires");
        pacienteSalidaDTO p = pacienteService.registrar(new pacienteEntradaDTO("Santiago", "Paz", "88888888", new Date(), domicilio));
        this.odontologoService.registrar(new odontologoEntradaDTO("3455647","Santiago" , "Paz"));
    }
    @Test
    public void altaTurnoTest(){
        this.cargarDataSet();
        turnoService.registrar(new turnoEntradaDTO(pacienteService.buscarPorId(1L).getId(), (long) odontologoService.buscarPorId(1L).getId(),new Date()));
        Assert.assertNotNull(turnoService.buscarPorId(1L));
    }
    @Test
    public void buscarTurnoTest(){
        Assert.assertNotNull(turnoService.buscarPorId(1L));
    }
    @Test
    public void eliminarTurnoTest(){
        turnoService.eliminar(1L);
        Assert.assertFalse(turnoService.buscarPorId(1L) != null);
    }
}
