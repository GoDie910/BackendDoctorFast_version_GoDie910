package com.doctorfast.org.controller;


import com.doctorfast.org.model.*;
import com.doctorfast.org.requests.*;
import com.doctorfast.org.service.*;
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
    private DiagnosticoService diagnosticoService;
    private PrescripcionMedicaService prescripcionMedicaService;
    private MedicamentoService medicamentoService;

    @Autowired
    public DoctorController(
            DoctorService doctorService,
            UsuarioService usuarioService,
            EspecialidadService especialidadService,
            CitaService citaService,
            DiagnosticoService diagnosticoService,
            PrescripcionMedicaService prescripcionMedicaService,
            MedicamentoService medicamentoService){
        this.doctorService=doctorService;
        this.especialidadService=especialidadService;
        this.usuarioService = usuarioService;
        this.citaService = citaService;
        this.diagnosticoService = diagnosticoService;
        this.prescripcionMedicaService = prescripcionMedicaService;
        this.medicamentoService = medicamentoService;
    }

    @GetMapping("/perfil/{id}")
    public Doctor obtenerMiInformacion(@PathVariable("id") int id) throws Exception{
        return doctorService.obtenerDoctorPerfil(id);
    }

    @GetMapping("/citas/disponibles/{id}")
    public List<Cita> obtenerMisCitasDisponibles(@PathVariable("id") int id) throws Exception {
        return citaService.listarCitasDisponiblesDoctor(id);
    }

    @GetMapping("/citas/pendientes/{id}")
    public List<Cita> obtenerMisCitasPendientes(@PathVariable("id") int id) throws Exception {
        return citaService.listarCitasPendientesDoctor(id);
    }

    @GetMapping("/citas/historial/{id}")
    public List<Cita> obtenerMiHistorialCitas(@PathVariable("id") int id) throws Exception {
        return citaService.listarHistorialCitasMedico(id);
    }

    @PutMapping("/status/{id}")
    public int cambiarMiStatus(@PathVariable("id") int id) throws Exception {
        return doctorService.cambiarStatus(id);
    }

    @PutMapping("/citas/aceptar/{usuario_id}&{cita_id}")
    public int aceptarCita(@PathVariable("usuario_id") int usuario_id, @PathVariable("cita_id") int cita_id) throws Exception {
        return citaService.aceptarCitaDoctor(usuario_id, cita_id);
    }

    @PutMapping("/citas/cancelar/{usuario_id}&{cita_id}")
    public int cancelarCita(@PathVariable("usuario_id") int usuario_id, @PathVariable("cita_id") int cita_id) throws Exception {
        return citaService.cancelarCitaDoctor(usuario_id, cita_id);
    }

    @PostMapping("/citas/crear_diagnosticos")
    public List<Diagnostico> crearDiagnosticos(@RequestBody List<CitaDiagnosticoRequest> diagnosticos) throws Exception{
        return diagnosticoService.crearDiagnosticos(diagnosticos);
    }

    @PostMapping("/citas/crear_prescripciones_medicas")
    public List<PrescripcionMedica> crearPrescripcionesMedicas(@RequestBody List<CitaPrescripcionMedicaRequest> prescripcionesMedicas) throws Exception{
        return prescripcionMedicaService.crearPrescripcionesMedicas(prescripcionesMedicas);
    }

    @PutMapping("/citas/realizar_cita")
    public Cita realizarCita(@RequestBody CitaRealizarRequest cita) throws Exception {
        return citaService.realizarCita(cita);
    }

    @GetMapping("/citas/historial_medico/{id}")
    public List<HistoriaMedicaResponse> listHistorialMedico(@PathVariable("id") int id) throws Exception {
        return citaService.HistorialMedicoPacienteVistaDoctor(id);
    }

    @GetMapping("/medicamento/list")
    public List<Medicamento> listMedicamentos() throws Exception {




        return medicamentoService.listMedicamentos();
    }

}
