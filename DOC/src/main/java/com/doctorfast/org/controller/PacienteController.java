package com.doctorfast.org.controller;


import com.doctorfast.org.model.*;
import com.doctorfast.org.requests.CitaCreateRequest;
import com.doctorfast.org.requests.DoctorRating;
import com.doctorfast.org.requests.RatingRequest;
import com.doctorfast.org.requests.RatingResponse;
import com.doctorfast.org.service.AreaSintomaService;
import com.doctorfast.org.service.CitaService;
import com.doctorfast.org.service.DoctorService;
import com.doctorfast.org.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService;
    private DoctorService doctorService;
    private CitaService citaService;
    private AreaSintomaService areaSintomaService;

    @Autowired
    public PacienteController (
            PacienteService pacienteService,
            DoctorService doctorService,
            CitaService citaService,
            AreaSintomaService areaSintomaService) {
        this.pacienteService = pacienteService;
        this.doctorService = doctorService;
        this.citaService = citaService;
        this.areaSintomaService = areaSintomaService;
    }

    @PostMapping("/doctor/calificar")
    public RatingResponse registrarCalificacion(@RequestBody RatingRequest rating) throws Exception{
        RatingResponse calificacion = doctorService.calificarDoctor(rating);

        if(calificacion.getIdRating() <= 0){
            return null;
        }

        return calificacion;
    }

    @GetMapping("/doctor/disponibles")
    public List<Doctor> obtenerDoctoresDisponibles() throws Exception {
        return doctorService.listarDoctoresDisponibles();
    }

    @GetMapping("/perfil/{id}")
    public Paciente obtenerMiInformacion(@PathVariable("id") int id) throws Exception{

        return pacienteService.obtenerPacientePerfil(id);
    }

    @GetMapping("/citas/en_curso/{id}")
    public List<Cita> obtenerMiCtiasEnCurso(@PathVariable("id") int id) throws Exception {
        return citaService.listarCitasEnCursoPaciente(id);
    }

    @GetMapping("/citas/historial/{id}")
    public List<Cita> obtenerMiHistorialCitas(@PathVariable("id") int id) throws Exception {
        return citaService.listarHistorialCitasPaciente(id);
    }

    @PostMapping("/citas/crear")
    public Cita crearCita(@RequestBody CitaCreateRequest cita) throws Exception {
        return citaService.crearCitaPaciente(cita);
    }

    @GetMapping("/area_sintoma/list")
    public List<AreaSintoma> listAreaSintoma() throws Exception {
        return areaSintomaService.listarAreaSintoma();
    }
}
