package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.Paciente;
import com.doctorfast.org.repository.PacienteRepository;
import com.doctorfast.org.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {

        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public List<Paciente> listarPacientes() throws Exception {
        return pacienteRepository.findAll();
    }

    @Override
    public Long numeroDePacientes() {
        return pacienteRepository.count();
    }
}
