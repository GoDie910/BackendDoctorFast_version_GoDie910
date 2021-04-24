package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.*;
import com.doctorfast.org.repository.*;
import com.doctorfast.org.requests.RolesCantidades;
import com.doctorfast.org.service.RolService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class RolServiceImpl implements RolService {

    private RolRepository rolRepository;
    private PacienteRepository pacienteRepository;
    private DoctorRepository doctorRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public RolServiceImpl (
            RolRepository rolRepository,
            PacienteRepository pacienteRepository,
            DoctorRepository doctorRepository,
            UsuarioRepository usuarioRepository
    ){
        this.rolRepository = rolRepository;
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public RolesCantidades rolesCantidades() throws Exception {
        RolesCantidades rolesCantidades = new RolesCantidades();

        long numDoctores = doctorRepository.count();
        long numPacientes = pacienteRepository.count();
        long numUsuarios = usuarioRepository.count();

        rolesCantidades.setDoctores(numDoctores);
        rolesCantidades.setPacientes(numPacientes);
        rolesCantidades.setUsuarios(numUsuarios);

        return rolesCantidades;
    }
}
