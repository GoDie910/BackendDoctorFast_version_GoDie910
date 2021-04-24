package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.AreaSintoma;
import com.doctorfast.org.repository.AreaSintomaRepository;
import com.doctorfast.org.service.AreaSintomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaSintomaServiceImpl implements AreaSintomaService {

    private AreaSintomaRepository areaSintomaRepository;

    @Autowired
    public AreaSintomaServiceImpl (
            AreaSintomaRepository areaSintomaRepository
    )
    {
        this.areaSintomaRepository = areaSintomaRepository;
    }

    @Override
    public List<AreaSintoma> listarAreaSintoma() throws Exception {
        return areaSintomaRepository.findAll();
    }
}
