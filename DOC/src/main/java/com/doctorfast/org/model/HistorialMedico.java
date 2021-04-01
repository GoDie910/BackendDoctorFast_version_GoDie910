package com.doctorfast.org.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "historialMedico")
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historialMedico_id",columnDefinition = "serial")
    private Integer idHistorialMedico;

    @ManyToOne
    @JoinColumn(name = "historiaMedica_id")
    private HistoriaMedica historiaMedica;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
