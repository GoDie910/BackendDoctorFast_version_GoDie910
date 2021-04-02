package com.doctorfast.org.service;

import com.doctorfast.org.model.*;
import com.doctorfast.org.requests.NuevoPasswordRequest;

import java.util.List;

public interface UsuarioService {

    Usuario registrarUsuario(Usuario usuario) throws Exception;

    Paciente registrarPaciente(Paciente paciente) throws Exception;

    Doctor registrarDoctor(Doctor doctor) throws  Exception;

    Administrador registrarAdministrador(Administrador administrador) throws Exception;

    List<Rol> listarRoles() throws  Exception;

    List<Paciente> listarPacientes() throws Exception;

    List<Usuario> listarUsuarios() throws Exception;

    int cambiarPassword(NuevoPasswordRequest nuevoPasswordRequest) throws  Exception;
}
