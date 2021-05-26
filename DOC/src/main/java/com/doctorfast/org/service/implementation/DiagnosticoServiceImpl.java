package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.Cita;
import com.doctorfast.org.model.Diagnostico;
import com.doctorfast.org.repository.CitaRepository;
import com.doctorfast.org.repository.DiagnosticoRepository;
import com.doctorfast.org.requests.CitaDiagnosticoRequest;
import com.doctorfast.org.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosticoServiceImpl implements DiagnosticoService {

    private DiagnosticoRepository diagnosticoRepository;
    private CitaRepository citaRepository;

    @Autowired
    public DiagnosticoServiceImpl(
            DiagnosticoRepository diagnosticoRepository,
            CitaRepository citaRepository
    ){
        this.diagnosticoRepository = diagnosticoRepository;
        this.citaRepository = citaRepository;
    }

    @Override
    public List<Diagnostico> crearDiagnosticos(List<CitaDiagnosticoRequest> diagnosticos) throws Exception {
        List<Diagnostico> diagnosticoList = new ArrayList<>();
        Cita cita = new Cita();
        Diagnostico diagnostico = new Diagnostico();

        for (int i = 0; i < diagnosticos.size(); i++){
            diagnostico.setNombre(diagnosticos.get(i).getNombre());
            diagnostico.setDescripcion(diagnosticos.get(i).getDescripcion());
            cita = citaRepository.findById(diagnosticos.get(i).getCita_id());
            diagnostico.setCita(cita);


            diagnosticoList.add(diagnostico);
            cita = new Cita();
            diagnostico = new Diagnostico();
        }
        return diagnosticoRepository.saveAll(diagnosticoList);
    }
}
