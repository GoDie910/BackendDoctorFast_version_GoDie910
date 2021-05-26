package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.Cita;
import com.doctorfast.org.model.Medicamento;
import com.doctorfast.org.model.PrescripcionMedica;
import com.doctorfast.org.repository.CitaRepository;
import com.doctorfast.org.repository.MedicamentoRepository;
import com.doctorfast.org.repository.PrescripcionMedicaRepository;
import com.doctorfast.org.requests.CitaPrescripcionMedicaRequest;
import com.doctorfast.org.service.PrescripcionMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescripcionMedicaServiceImpl implements PrescripcionMedicaService {

    private PrescripcionMedicaRepository prescripcionMedicaRepository;
    private CitaRepository citaRepository;
    private MedicamentoRepository medicametoRepository;

    @Autowired
    public PrescripcionMedicaServiceImpl(
            PrescripcionMedicaRepository prescripcionMedicaRepository,
            CitaRepository citaRepository,
            MedicamentoRepository medicametoRepository
    ){
        this.prescripcionMedicaRepository = prescripcionMedicaRepository;
        this.citaRepository = citaRepository;
        this.medicametoRepository = medicametoRepository;
    }

    @Override
    public List<PrescripcionMedica> crearPrescripcionesMedicas(List<CitaPrescripcionMedicaRequest> prescripcionesMedicas) throws Exception {
        List<PrescripcionMedica> prescripcionMedicaList = new ArrayList<>();
        Cita cita = new Cita();
        Medicamento medicamento = new Medicamento();
        PrescripcionMedica prescripcionMedica = new PrescripcionMedica();

        for (int i = 0; i < prescripcionesMedicas.size(); i++){
            prescripcionMedica.setDescripcion(prescripcionesMedicas.get(i).getDescripcion());
            prescripcionMedica.setCantidad(prescripcionesMedicas.get(i).getCantidad());
            prescripcionMedica.setFechaInicio(prescripcionesMedicas.get(i).getFechaInicio());
            cita = citaRepository.findById(prescripcionesMedicas.get(i).getCita_id());
            prescripcionMedica.setCita(cita);
            medicamento = medicametoRepository.findById(prescripcionesMedicas.get(i).getMedicamento_id());
            prescripcionMedica.setMedicamento(medicamento);

            prescripcionMedicaList.add(prescripcionMedica);
            cita = new Cita();
            medicamento = new Medicamento();
            prescripcionMedica = new PrescripcionMedica();
        }

        return prescripcionMedicaRepository.saveAll(prescripcionMedicaList);
    }
}
