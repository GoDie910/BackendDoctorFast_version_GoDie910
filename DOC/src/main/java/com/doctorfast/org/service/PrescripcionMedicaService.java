package com.doctorfast.org.service;

import com.doctorfast.org.model.PrescripcionMedica;
import com.doctorfast.org.requests.CitaPrescripcionMedicaRequest;

import java.util.List;

public interface PrescripcionMedicaService {

    List<PrescripcionMedica> crearPrescripcionesMedicas(List<CitaPrescripcionMedicaRequest> prescripcionMedicas) throws Exception;
}
