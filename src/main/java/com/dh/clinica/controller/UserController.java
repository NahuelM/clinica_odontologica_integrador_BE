package com.dh.clinica.controller;

import com.dh.clinica.dto.salida.odontologoSalidaDTO;
import com.dh.clinica.model.AppUser;
import com.dh.clinica.service.IOdontologoService;
import com.dh.clinica.service.IPacienteService;
import com.dh.clinica.service.imp.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/admin")
public class UserController {
    private AppUserService appUserService;
    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    @GetMapping
    public ResponseEntity<List<AppUser>> listarOdontologos(){
        return new ResponseEntity<>(appUserService.listar(), HttpStatus.OK);
    }


}
