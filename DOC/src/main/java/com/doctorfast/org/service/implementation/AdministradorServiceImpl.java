package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.*;
import com.doctorfast.org.repository.*;
import com.doctorfast.org.requests.NuevoPasswordRequest;
import com.doctorfast.org.service.AdministradorService;
import com.doctorfast.org.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    private AdministradorRepository administradorRepository;

    private PasswordEncoder bcryptEncoder;

    @Autowired
    public AdministradorServiceImpl(
            AdministradorRepository administradorRepository,
            PasswordEncoder bcryptEncoder)
    {
        this.administradorRepository = administradorRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public List<Administrador> listarAdministradores() throws Exception {
        return administradorRepository.findAll();
    }

    @Override
    public Administrador registrarAdministrador(Administrador administrador) throws Exception {
        String password = bcryptEncoder.encode(administrador.getPassword());
        administrador.setPassword(password);

        return administradorRepository.save(administrador);
    }

    @Override
    public Administrador modificarAdministrador(Administrador administrador) throws Exception {
        Administrador admin_bd = administradorRepository.getOne(administrador.getIdAdministrador());

        administrador.setPassword(admin_bd.getPassword());

        return administradorRepository.save(administrador);
    }
}
