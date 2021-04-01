package com.doctorfast.org.controller;

import com.doctorfast.org.model.Doctor;
import com.doctorfast.org.model.Paciente;
import com.doctorfast.org.model.Rol;
import com.doctorfast.org.model.Usuario;
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
    public void registrarPaciente(@RequestBody Paciente paciente) throws Exception{
        usuarioService.registrarPaciente(paciente);
    }

    @PostMapping("/registroDoctor")
    public void registrarDoctor(@RequestBody Doctor doctor) throws Exception{
        usuarioService.registrarDoctor(doctor);
    }

    @CrossOrigin
    @GetMapping("/roles")
    public List<Rol> listarRoles() throws Exception{
        return usuarioService.listarRoles();
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Usuario> ListarUsuarios() throws Exception{
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/pacientes")
    public ResponseEntity<List<Paciente>> listarPacientes() throws Exception{
        return ResponseEntity.ok(usuarioService.listarPacientes());
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
