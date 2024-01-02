package com.dh.clinica;

import com.dh.clinica.dto.entrada.turnoEntradaDTO;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.dto.entrada.pacienteEntradaDTO;
import com.dh.clinica.dto.salida.pacienteSalidaDTO;
import com.dh.clinica.dto.entrada.domicilioEntradaDTO;
import com.dh.clinica.dto.entrada.odontologoEntradaDTO;
import com.dh.clinica.dto.salida.odontologoSalidaDTO;

import com.dh.clinica.model.Turno;
import com.dh.clinica.service.IOdontologoService;
import com.dh.clinica.service.IPacienteService;
import com.dh.clinica.service.ITurnoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDataSet() {
        domicilioEntradaDTO domicilio = new domicilioEntradaDTO("Av Santa fe", 444, "CABA", "Buenos Aires");
        pacienteSalidaDTO p = pacienteService.registrar(new pacienteEntradaDTO("Santiago", "Paz", "88888888", new Date(), domicilio));
        this.odontologoService.registrar(new odontologoEntradaDTO("1234567", "Paz", "Apellido"));
        turnoService.registrar(new turnoEntradaDTO(pacienteService.buscarPorId(1L).getId(), (long) odontologoService.buscarPorId(1L).getId(),new Date()));

    }
    @Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
