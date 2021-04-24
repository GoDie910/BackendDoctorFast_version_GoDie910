package com.doctorfast.org.service;

import com.doctorfast.org.model.Cita;
import com.doctorfast.org.requests.CitaCreateRequest;

import java.util.List;
import java.util.Optional;

public interface CitaService {


    List<Cita> listarCitas() throws Exception;

    Long numeroDeCitas();

    List<Cita> listarHistorialCitasPaciente(int id);

    List<Cita> listarHistorialCitasMedico(int id);

    List<Cita> listarCitasEnCursoPaciente(int id);

    List<Cita> listarCitasEnCursoDoctor(int id);

    Cita crearCitaPaciente(CitaCreateRequest cita) throws Exception;
}
