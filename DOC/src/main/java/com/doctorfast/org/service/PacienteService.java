package com.doctorfast.org.service;


import com.doctorfast.org.model.Paciente;

import java.util.List;

public interface PacienteService {

    List<Paciente> listarPacientes () throws Exception;

    Long numeroDePacientes();

    Paciente obtenerPacientePerfil(int id) throws Exception;

}
