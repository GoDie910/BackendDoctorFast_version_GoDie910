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
    private UsuarioRepository usuarioRepository;

    private PasswordEncoder bcryptEncoder;

    @Autowired
    public AdministradorServiceImpl(
            AdministradorRepository administradorRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder bcryptEncoder)
    {
        this.administradorRepository = administradorRepository;
        this.usuarioRepository = usuarioRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public List<Administrador> listarAdministradores() throws Exception {
        return administradorRepository.findAll();
    }



    @Override
    public Administrador modificarAdministrador(Administrador administrador) throws Exception {
        Administrador admin_bd = administradorRepository.getOne(administrador.getIdAdministrador());

        admin_bd.getUsuario().setNombreUsuario(administrador.getUsuario().getNombreUsuario());
        admin_bd.getUsuario().setPassword(administrador.getUsuario().getPassword());
        admin_bd.getUsuario().setNombre(administrador.getUsuario().getNombre());
        admin_bd.getUsuario().setApellidoPaterno(administrador.getUsuario().getApellidoPaterno());
        admin_bd.getUsuario().setApellidoMaterno(administrador.getUsuario().getApellidoMaterno());
        admin_bd.getUsuario().setDni(administrador.getUsuario().getDni());
        admin_bd.getUsuario().setCorreo(administrador.getUsuario().getCorreo());
        admin_bd.getUsuario().setCelular(administrador.getUsuario().getCelular());
        admin_bd.getUsuario().setFechaNacimiento(administrador.getUsuario().getFechaNacimiento());
        admin_bd.getUsuario().setDistrito(administrador.getUsuario().getDistrito());
        admin_bd.getUsuario().setDireccion(administrador.getUsuario().getDireccion());

        return administradorRepository.save(administrador);
    }

}
