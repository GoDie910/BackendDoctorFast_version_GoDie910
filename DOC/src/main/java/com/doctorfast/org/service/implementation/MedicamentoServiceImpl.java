package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.Medicamento;
import com.doctorfast.org.repository.MedicamentoRepository;
import com.doctorfast.org.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    private MedicamentoRepository medicamentoRepository;

    @Autowired
    public MedicamentoServiceImpl(
            MedicamentoRepository medicamentoRepository
    ) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public List<Medicamento> listMedicamentos() throws Exception {
        return medicamentoRepository.findAll();
    }
}
