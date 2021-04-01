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
@Table(name = "historiaMedica")
public class HistoriaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historiaMedica_id",columnDefinition = "serial")
    private Integer idHistoriaMedica;

    private String codigoHistoria;

    private Date fechaCreacion;

    private String nombre;

    private String descripcion;
}
