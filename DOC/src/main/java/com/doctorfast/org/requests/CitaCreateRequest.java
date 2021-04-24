package com.doctorfast.org.requests;

import lombok.Data;
import java.time.LocalTime;
import java.util.Date;

@Data
public class CitaCreateRequest {

    private LocalTime horaInicio;

    private Date fechaCita;

    private Integer areaSintoma_id;

    private String descripcion;

    private Integer doctor_id;

    private Integer paciente_usuario_id;

}
