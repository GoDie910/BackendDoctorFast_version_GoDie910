package com.doctorfast.org.requests;

import lombok.Data;

@Data
public class CitaDiagnosticoRequest {

    private int cita_id;

    private String nombre;

    private String descripcion;

}
