package com.doctorfast.org.controller;


import com.doctorfast.org.model.Cita;
import com.doctorfast.org.model.StringResponse;
import com.doctorfast.org.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cita")
public class CitaControlller {

    private CitaService citaService;

    @Autowired
    public CitaControlller(CitaService citaService){
        this.citaService=citaService;
    }



    @GetMapping("/list")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Cita> ListaCitas() throws Exception{
        return citaService.listarCitas();
    }

    @GetMapping("/cita-numero")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> numeroDeCitas(){
        Long number = citaService.numeroDeCitas();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
