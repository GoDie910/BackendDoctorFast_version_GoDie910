package com.doctorfast.org.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicamentosItems")
public class MedicamentosItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicamentosItems_id",columnDefinition = "serial")
    private Integer idMedicamentosItems;

    @ManyToOne
    @JoinColumn(name = "prescripcionMedica_id")
    private PrescripcionMedica prescripcionMedica;

    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;
}
