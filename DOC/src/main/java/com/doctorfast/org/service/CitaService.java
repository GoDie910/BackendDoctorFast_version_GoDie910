package com.doctorfast.org.service;

import com.doctorfast.org.model.Cita;
import com.doctorfast.org.requests.CitaCreateRequest;
import com.doctorfast.org.requests.CitaRealizarRequest;
import com.doctorfast.org.requests.HistoriaMedicaResponse;

import java.util.List;
import java.util.Optional;

public interface CitaService {


    List<Cita> listarCitas() throws Exception;

    Long numeroDeCitas();




    List<Cita> listarHistorialCitasMedico(int id);

    List<Cita> listarCitasDisponiblesDoctor(int id);

    List<Cita> listarCitasPendientesDoctor(int id) throws Exception;

    int aceptarCitaDoctor(int usuario_id, int cita_id) throws Exception;

    int cancelarCitaDoctor(int usuario_id, int cita_id) throws Exception;

    Cita realizarCita(CitaRealizarRequest cita) throws Exception;



    Cita crearCitaPaciente(CitaCreateRequest cita) throws Exception;

    List<Cita> listarCitasDisponiblesPaciente(int id);

    List<Cita> listarCitasPendientesPaciente(int id);

    List<Cita> listarHistorialCitasPaciente(int id);

    List<HistoriaMedicaResponse> HistorialMedicoPaciente(int id) throws Exception;

    List<HistoriaMedicaResponse> HistorialMedicoPacienteVistaDoctor(int id) throws Exception;






}
