package com.doctorfast.org.repository;

import com.doctorfast.org.model.PrescripcionMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrescripcionMedicaRepository extends JpaRepository<PrescripcionMedica, Integer>{


}
