package com.doctorfast.org.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicamento")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicamento_id",columnDefinition = "serial")
    private Integer idMedicamento;

    @Column(name = "codigoMedicamento")
    private String codigoMedicamento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "forma")
    private String forma;

    @Column(name = "presentación")
    private String presentación;

    @Column(name = "fraccion")
    private String fraccion;

    @Column(name = "laboratorio")
    private String laboratorio;


}
