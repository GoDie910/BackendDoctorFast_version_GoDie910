package com.doctorfast.org.controller;

import com.doctorfast.org.model.Administrador;
import com.doctorfast.org.model.Especialidad;
import com.doctorfast.org.service.AdministradorService;
import com.doctorfast.org.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdministradorController {

    private AdministradorService administradorService;
    private EspecialidadService especialidadService;

    @Autowired
    public AdministradorController(
            AdministradorService administradorService,
            EspecialidadService especialidadService) {
        this.administradorService = administradorService;
        this.especialidadService = especialidadService;
    }

    @GetMapping("/list")
    public List<Administrador> listarAdministradores() throws Exception{
        return administradorService.listarAdministradores();
    }

    @PutMapping(path = {"/edit/{id}"})
    public Administrador editar(@RequestBody Administrador administrador, @PathVariable("id") int id) throws Exception{
        administrador.setIdAdministrador(id);
        return administradorService.modificarAdministrador(administrador);
    }

    @GetMapping("/especialidades")
    public List<Especialidad> listarEspecialidades() throws Exception{
        return especialidadService.listarEspecialidades();
    }
}
