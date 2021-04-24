package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.Paciente;
import com.doctorfast.org.repository.PacienteRepository;
import com.doctorfast.org.repository.UsuarioRepository;
import com.doctorfast.org.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private PacienteRepository pacienteRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public PacienteServiceImpl(
            PacienteRepository pacienteRepository,
            UsuarioRepository usuarioRepository) {

        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<Paciente> listarPacientes() throws Exception {
        return pacienteRepository.findAll();
    }

    @Override
    public Long numeroDePacientes(){
        return pacienteRepository.count();
    }

    @Override
    public Paciente obtenerPacientePerfil(int id) throws Exception {
        return pacienteRepository.findByUsuarioId(id);
    }
}
