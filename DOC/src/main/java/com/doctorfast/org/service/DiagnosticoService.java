package com.doctorfast.org.service;

import com.doctorfast.org.model.Diagnostico;
import com.doctorfast.org.requests.CitaDiagnosticoRequest;

import java.util.List;

public interface DiagnosticoService {

    List<Diagnostico> crearDiagnosticos(List<CitaDiagnosticoRequest> diagnosticos) throws Exception;
}
