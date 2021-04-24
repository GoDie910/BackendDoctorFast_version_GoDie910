package com.doctorfast.org.controller;


import com.doctorfast.org.model.Cita;
import com.doctorfast.org.model.Doctor;
import com.doctorfast.org.model.StringResponse;
import com.doctorfast.org.requests.DoctorRating;
import com.doctorfast.org.requests.RatingRequest;
import com.doctorfast.org.requests.RatingResponse;
import com.doctorfast.org.service.CitaService;
import com.doctorfast.org.service.DoctorService;
import com.doctorfast.org.service.EspecialidadService;
import com.doctorfast.org.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin( origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;
    private UsuarioService usuarioService;
    private EspecialidadService especialidadService;
    private CitaService citaService;

    @Autowired
    public DoctorController(
            DoctorService doctorService,
            UsuarioService usuarioService,
            EspecialidadService especialidadService,
            CitaService citaService){
        this.doctorService=doctorService;
        this.especialidadService=especialidadService;
        this.usuarioService=usuarioService;
        this.citaService = citaService;
    }

    @GetMapping("/perfil/{id}")
    public Doctor obtenerMiInformacion(@PathVariable("id") int id) throws Exception{
        return doctorService.obtenerDoctorPerfil(id);
    }

    @GetMapping("/citas/en_curso/{id}")
    public List<Cita> obtenerMiCtiasEnCurso(@PathVariable("id") int id) throws Exception {
        return citaService.listarCitasEnCursoDoctor(id);
    }

    @GetMapping("/citas/historial/{id}")
    public List<Cita> obtenerMiHistorialCitas(@PathVariable("id") int id) throws Exception {
        return citaService.listarHistorialCitasMedico(id);
    }

}
