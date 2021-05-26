package com.doctorfast.org.requests;

import com.doctorfast.org.model.Medicamento;
import lombok.Data;

import java.util.Date;

@Data
public class HistoriaMedicaPrescripcionMedicaResponse {

    private Integer idPrescripcionMedica;

    private Integer cantidad;

    private String descripcion;

    private Date fechaInicio;

    Medicamento medicamento;

}
