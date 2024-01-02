package com.dh.clinica;



import com.dh.clinica.dto.entrada.pacienteEntradaDTO;
import com.dh.clinica.dto.salida.pacienteSalidaDTO;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.IPacienteService;
import  com.dh.clinica.dto.entrada.domicilioEntradaDTO;
import  com.dh.clinica.dto.salida.domicilioSalidaDTO;

import org.junit.Assert;


import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private IPacienteService pacienteService;


    public void cargarDataSet() {
        domicilioEntradaDTO domicilio = new domicilioEntradaDTO("Av Santa fe", 444, "CABA", "Buenos Aires");
        pacienteSalidaDTO p = pacienteService.registrar(new pacienteEntradaDTO("Santiago", "Paz", "88888888", new Date(), domicilio));
        domicilioEntradaDTO domicilio1 = new domicilioEntradaDTO("Av Avellaneda", 333, "CABA", "Buenos Aires");
        pacienteSalidaDTO p1 = pacienteService.registrar(new pacienteEntradaDTO("Micaela", "Perez", "99999999", new Date(), domicilio1));

    }

    @Test
    public void agregarYBuscarPacienteTest() {
        this.cargarDataSet();
        domicilioEntradaDTO domicilio = new domicilioEntradaDTO("Calle", 123, "Temperley", "Buenos Aires");
        pacienteSalidaDTO p = pacienteService.registrar(new pacienteEntradaDTO("Tomas", "Pereyra", "12345678", new Date(), domicilio));

        Assert.assertNotNull(pacienteService.buscarPorId(p.getId()));
    }

    @Test
    public void eliminarPacienteTest() {
        pacienteService.eliminar(3L);
        Assert.assertTrue(pacienteService.buscarPorId(3L) == null);

    }

    @Test
    public void traerTodos() {
        List<pacienteSalidaDTO> pacientes = pacienteService.listar();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertFalse(pacientes.size() == 2);
    }


}
