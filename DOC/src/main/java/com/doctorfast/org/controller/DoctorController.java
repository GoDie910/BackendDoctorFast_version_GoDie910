package com.doctorfast.org.controller;


import com.doctorfast.org.model.Doctor;
import com.doctorfast.org.model.StringResponse;
import com.doctorfast.org.requests.DoctorRating;
import com.doctorfast.org.requests.RatingRequest;
import com.doctorfast.org.requests.RatingResponse;
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

    @Autowired
    public DoctorController(DoctorService doctorService, UsuarioService usuarioService, EspecialidadService especialidadService){
        this.doctorService=doctorService;
        this.especialidadService=especialidadService;
        this.usuarioService=usuarioService;
    }

    @PostMapping("/calificar")
    public RatingResponse registrarCalificacion(@RequestBody RatingRequest rating) throws Exception{
        RatingResponse calificacion = doctorService.calificarDoctor(rating);

        if(calificacion.getIdRating() <= 0){
            return null;
        }

        return calificacion;
    }

    @GetMapping("/ratingpromedio/{id}")
    public String promedioRating(@PathVariable Integer id) throws Exception{
        return doctorService.calificacionpromedio(id);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Doctor> ListaDoctores() throws Exception{
        return doctorService.listarDoctores();
    }

    @GetMapping(path = {"/detail/{id}"})
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity getById(@PathVariable("id") int id) throws Exception {
        if(!doctorService.existsById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Doctor doctor = doctorService.getOne(id).get();
        return new ResponseEntity(doctor, HttpStatus.OK);
    }

    @PutMapping(path = {"/edit/{id}"})
    public Doctor editar (@RequestBody Doctor d, @PathVariable("id") int id) throws Exception{
        d.setIdDoctor(id);
        return doctorService.modificarDoctor(d);
    }

    @GetMapping(path = {"/{id}"})
    public Optional<Doctor> listarId(@PathVariable("id") int id)throws Exception{
        return doctorService.getOne(id);
    }

    //obtener la cantidad de doctores
    @GetMapping("/doctor-numero")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> numberDeDoctores(){
        Long number = doctorService.numeroDeDoctores();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/por_distrito/{distrito}")
    public List<Doctor> listaDoctoresPorDistrito(@PathVariable("distrito") String distrito) throws Exception {
        return  doctorService.listarDoctoresPorDistrito(distrito);
    }

    @GetMapping("/por_rating/{rating}")
    public List<DoctorRating> listaDoctoresPorRanking(@PathVariable("rating") int rating) throws Exception {
        return doctorService.listarDoctoresPorRanking(rating);
    }


}
