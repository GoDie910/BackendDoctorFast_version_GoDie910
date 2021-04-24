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
public class CitaController {

    private CitaService citaService;

    @Autowired
    public CitaController(CitaService citaService){
        this.citaService=citaService;
    }


}
