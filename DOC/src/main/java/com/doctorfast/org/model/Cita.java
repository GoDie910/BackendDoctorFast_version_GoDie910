package com.doctorfast.org.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cita_id",columnDefinition = "serial")
    private Integer idCita;

    @Column(name="status")
    private Integer status;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name = "horaInicio")
    private LocalTime horaInicio;

    @Column(name = "horaFin")
    private LocalTime horaFin;

    @Column(name = "codigoCita")
    private String codigoCita;

    @Column(name = "fechaCita")
    private Date fechaCita;

    @ManyToOne
    @JoinColumn(name = "areaSintoma_id")
    private AreaSintoma areaSintoma;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

}
