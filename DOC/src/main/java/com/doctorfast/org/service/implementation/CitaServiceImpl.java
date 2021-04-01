package com.doctorfast.org.service.implementation;


import com.doctorfast.org.model.Cita;
import com.doctorfast.org.repository.CitaRepository;
import com.doctorfast.org.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private CitaRepository citaRepository;

    @Autowired
    public CitaServiceImpl(CitaRepository citaRepository){

        this.citaRepository=citaRepository;
    }

    @Override
    public List<Cita> listarCitas() throws Exception {
        return citaRepository.findAll();
    }

    @Override
    public Long numeroDeCitas() {
        return citaRepository.count();
    }
}
