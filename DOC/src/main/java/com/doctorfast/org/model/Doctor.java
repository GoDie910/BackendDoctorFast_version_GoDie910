package com.doctorfast.org.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id",columnDefinition = "serial")
    private Integer idDoctor;

    @Column(name = "dniFoto")
    private String dniFoto;

    @Column(name = "disponibilidad")
    private Boolean disponibilidad;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @Column(name = "numeroColegiatura")
    private String numeroColegiatura;

    @Column(name = "centroLabor")
    private String centroLabor;

    @Column(name = "tarifa")
    private Double tarifa;
}
