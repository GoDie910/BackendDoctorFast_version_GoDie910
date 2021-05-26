package com.doctorfast.org.requests;


import lombok.Data;

import java.util.List;
import java.util.Optional;


@Data
public class HistoriaMedicaResponse {

    HistoriaMedicaCitaResponse cita;

    Optional<List<HistoriaMedicaDiagnosticoResponse>> diagnosticos;

    Optional<List<HistoriaMedicaPrescripcionMedicaResponse>> prescripcionesMedicas;
}
