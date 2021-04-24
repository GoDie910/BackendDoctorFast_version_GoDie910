package com.doctorfast.org.controller;

import com.doctorfast.org.model.*;
import com.doctorfast.org.requests.DoctorRating;
import com.doctorfast.org.requests.RolesCantidades;
import com.doctorfast.org.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin( origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdministradorController {

    private AdministradorService administradorService;
    private EspecialidadService especialidadService;
    private CitaService citaService;
    private PacienteService pacienteService;
    private UsuarioService usuarioService;
    private DoctorService doctorService;
    private RolService rolService;

    @Autowired
    public AdministradorController(
            AdministradorService administradorService,
            EspecialidadService especialidadService,
            CitaService citaService,
            PacienteService pacienteService,
            UsuarioService usuarioService,
            DoctorService doctorService,
            RolService rolService) {
        this.administradorService = administradorService;
        this.especialidadService = especialidadService;
        this.citaService = citaService;
        this.pacienteService = pacienteService;
        this.usuarioService=usuarioService;
        this.doctorService = doctorService;
        this.rolService = rolService;
    }

    //APIS ADMIN GENERALES

    @GetMapping("/admin/list")
    public List<Administrador> listarAdministradores() throws Exception{
        return administradorService.listarAdministradores();
    }

    @PutMapping(path = {"/admin/edit/{id}"})
    public Administrador editar(@RequestBody Administrador administrador, @PathVariable("id") int id) throws Exception{
        administrador.setIdAdministrador(id);
        return administradorService.modificarAdministrador(administrador);
    }

    //APIS ESPECIALIDADES

    @GetMapping("/especialidades")
    public List<Especialidad> listarEspecialidades() throws Exception{
        return especialidadService.listarEspecialidades();
    }

    //APIS ROLES

    @GetMapping("/roles/por-numero")
    public RolesCantidades ListarRolesNumero() throws Exception{
        return rolService.rolesCantidades();
    }

    //APIS CITAS

    @GetMapping("/cita/list")
    public List<Cita> ListaCitas() throws Exception{
        return citaService.listarCitas();
    }

    @GetMapping("/cita/cita-numero")
    public ResponseEntity<?> numeroDeCitas(){
        Long number = citaService.numeroDeCitas();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //APIS PACIENTES

    @GetMapping("/paciente/list")
    public List<Paciente> ListaPacientes() throws Exception{
        return pacienteService.listarPacientes();
    }

    @GetMapping("/paciente/paciente-numero")
    public ResponseEntity<?> numberDePacientes(){
        Long number = pacienteService.numeroDePacientes();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //APIS USUARIO

    @CrossOrigin
    @GetMapping("/usuario/roles")
    public List<Rol> listarRoles() throws Exception{
        return usuarioService.listarRoles();
    }

    @GetMapping("/usuario/list")
    public List<Usuario> ListarUsuarios() throws Exception{
        return usuarioService.listarUsuarios();
    }

    //APIS DOCTOR

    @GetMapping("/doctor/list")
    public List<Doctor> ListaDoctores() throws Exception{
        return doctorService.listarDoctores();
    }

    @GetMapping(path = {"/doctor/{id}"})
    public Optional<Doctor> listarId(@PathVariable("id") int id)throws Exception{
        return doctorService.getOne(id);
    }

    @GetMapping(path = {"/doctor/detail/{id}"})
    public ResponseEntity getById(@PathVariable("id") int id) throws Exception {
        if(!doctorService.existsById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Doctor doctor = doctorService.getOne(id).get();
        return new ResponseEntity(doctor, HttpStatus.OK);
    }

    @PutMapping(path = {"/doctor/edit/{id}"})
    public Doctor editar (@RequestBody Doctor d, @PathVariable("id") int id) throws Exception{
        d.setIdDoctor(id);
        return doctorService.modificarDoctor(d);
    }

    @GetMapping("/doctor/ratingpromedio/{id}")
    public String promedioRating(@PathVariable Integer id) throws Exception{
        return doctorService.calificacionpromedio(id);
    }

    @GetMapping("/doctor/doctor-numero")
    public ResponseEntity<?> numberDeDoctores(){
        Long number = doctorService.numeroDeDoctores();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/doctor/por_distrito/{distrito}")
    public List<Doctor> listaDoctoresPorDistrito(@PathVariable("distrito") String distrito) throws Exception {
        return  doctorService.listarDoctoresPorDistrito(distrito);
    }

    @GetMapping("/doctor/por_rating/{rating}")
    public List<DoctorRating> listaDoctoresPorRanking(@PathVariable("rating") int rating) throws Exception {
        return doctorService.listarDoctoresPorRanking(rating);
    }

    @GetMapping("/doctor/por_rating/mejores")
    public List<DoctorRating> listarDoctoresPorRankingMejores() throws Exception {
        return doctorService.listarDoctoresPorRankingMejores();
    }

}
