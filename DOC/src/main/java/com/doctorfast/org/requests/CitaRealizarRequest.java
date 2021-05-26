package com.doctorfast.org.requests;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CitaRealizarRequest {

    private int cita_id;

    private LocalTime horaInicio;

    private LocalTime horaFin;
}
