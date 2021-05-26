package com.doctorfast.org.requests;

import com.doctorfast.org.model.Medicamento;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class CitaPrescripcionMedicaRequest {

    private int cita_id;

    private int medicamento_id;

    private Date fechaInicio;

    private int cantidad;

    private String descripcion;
}
