package com.doctorfast.org.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id",columnDefinition = "serial")
    private Integer idRating;

    @Column(name = "calificacion")
    private String calificacion;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
