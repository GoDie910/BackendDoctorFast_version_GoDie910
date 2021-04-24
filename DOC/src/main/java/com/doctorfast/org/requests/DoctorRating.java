package com.doctorfast.org.requests;

import com.doctorfast.org.model.Especialidad;
import com.doctorfast.org.model.Usuario;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class DoctorRating {

    private Integer idDoctor;

    private Double rating;

    private String dniFoto;

    private Boolean disponibilidad;

    private Boolean habilitado;

    private Usuario usuario;

    private Especialidad especialidad;

    private String numeroColegiatura;

    private String centroLabor;

    private Double tarifa;
}
