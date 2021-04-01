package com.doctorfast.org.controller;


import com.doctorfast.org.model.Doctor;
import com.doctorfast.org.model.Paciente;
import com.doctorfast.org.model.StringResponse;
import com.doctorfast.org.service.PacienteService;
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
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController (PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //Listar pacientes
    @GetMapping("/list")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Paciente> ListaPacientes() throws Exception{
        return pacienteService.listarPacientes();
    }

    //obtener la cantidad de pacientes
    @GetMapping("/paciente-numero")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> numberDePacientes(){
        Long number = pacienteService.numeroDePacientes();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
