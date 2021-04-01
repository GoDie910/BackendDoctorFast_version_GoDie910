package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.Especialidad;
import com.doctorfast.org.repository.EspecialidadRepository;
import com.doctorfast.org.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    private EspecialidadRepository especialidadRepository;

    @Autowired
    public EspecialidadServiceImpl(
            EspecialidadRepository especialidadRepository)
    {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public List<Especialidad> listarEspecialidades() throws Exception {
        return especialidadRepository.findAll();
    }

}
