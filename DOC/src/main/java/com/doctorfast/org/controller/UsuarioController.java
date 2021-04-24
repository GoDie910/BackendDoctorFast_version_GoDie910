package com.doctorfast.org.controller;

import com.doctorfast.org.model.*;
import com.doctorfast.org.requests.NuevoPasswordRequest;
import com.doctorfast.org.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @PostMapping("/registro")
    public void registrarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuarioService.registrarUsuario(usuario);
    }

    @PostMapping("/registroPaciente")
    public Paciente registrarPaciente(@RequestBody Paciente paciente) throws Exception{
        return usuarioService.registrarPaciente(paciente);
    }

    @PostMapping("/registroDoctor")
    public Doctor registrarDoctor(@RequestBody Doctor doctor) throws Exception{
        return usuarioService.registrarDoctor(doctor);
    }

    @PostMapping("/registroAdmin")
    public Administrador registrarAdministrador(@RequestBody Administrador administrador) throws Exception{
        administrador.setHabilitado(false);
        return usuarioService.registrarAdministrador(administrador);
    }

    @PutMapping("/cambiarpassword")
    public int actualizarPassword(@RequestBody NuevoPasswordRequest nuevoPasswordRequest) throws Exception{
        return usuarioService.cambiarPassword(nuevoPasswordRequest);
    }

    @GetMapping("/prueba")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('PACIENTE')")
    public String PRUEBA() {
        return ">>> BIENVENIDO PERRO";
    }
}
