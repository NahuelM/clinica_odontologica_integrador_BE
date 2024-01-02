package com.dh.clinica;

import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.IOdontologoService;
import  com.dh.clinica.dto.entrada.odontologoEntradaDTO;
import  com.dh.clinica.dto.salida.odontologoSalidaDTO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTests {
    @Autowired
    private IOdontologoService odontologoService;


    public void cargarDataSet() {
        this.odontologoService.registrar(new odontologoEntradaDTO("15478", "Paz", "Apellido"));


    }

    @Test
    public void agregarOdontologo() {
        this.cargarDataSet();
        odontologoService.registrar(new odontologoEntradaDTO("58754", "Juan", "Ramirez"));
        List<odontologoSalidaDTO> odontologos = odontologoService.listar();
        Assert.assertTrue(odontologos.size() != 2);

    }

    @Test
    public void eliminarOdontologoTest() {
        odontologoService.eliminar(1L);
        Assert.assertTrue(odontologoService.buscarPorId(1L)==null);

    }

    @Test
    public void traerTodos() {
        List<odontologoSalidaDTO> odontologos = odontologoService.listar();
        Assert.assertTrue(odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() != 1);
    }

}
