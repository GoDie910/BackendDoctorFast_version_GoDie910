package com.doctorfast.org.service;

import com.doctorfast.org.model.Administrador;

import java.util.List;

public interface AdministradorService {

    List<Administrador> listarAdministradores() throws Exception;

    Administrador modificarAdministrador(Administrador administrador) throws Exception;
}
