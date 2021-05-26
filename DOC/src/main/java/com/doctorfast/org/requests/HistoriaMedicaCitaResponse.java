package com.doctorfast.org.requests;

import com.doctorfast.org.model.AreaSintoma;
import com.doctorfast.org.model.Especialidad;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class HistoriaMedicaCitaResponse {

    private Integer idCita;

    private Integer status;

    private String descripcion;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private String codigoCita;

    private Date fechaCita;

    private String paciente_nombre;

    AreaSintoma areaSintoma;

    HistoriaMedicaDoctorResponse doctor;

}
