package com.doctorfast.org.requests;


import com.doctorfast.org.model.Especialidad;
import lombok.Data;

@Data
public class HistoriaMedicaDoctorResponse {

    private Integer idDoctor;

    private String doctor_nombre;

    private String doctor_apellido_paterno;

    private String doctor_apellido_materno;

    private Especialidad doctor_especialidad;

    private String doctor_centroLabor;

}
