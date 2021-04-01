package com.doctorfast.org.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prescripcionMedica")
public class PrescripcionMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescripcionMedica_id",columnDefinition = "serial")
    private Integer idPrescripcionMedica;

    @Column(name = "codigoPrescripcion")
    private String codigoPrescripcion;

    @Column(name = "fechaInicio")
    private Date fechaInicio;

    @Column(name = "fechaFin")
    private Date fechaFin;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "frecuencia")
    private String frecuencia;
}
