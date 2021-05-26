package com.doctorfast.org.repository;

import com.doctorfast.org.model.PrescripcionMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PrescripcionMedicaRepository extends JpaRepository<PrescripcionMedica, Integer>{

    @Query(value = "select * from prescripcion_medica where cita_id = ?1", nativeQuery = true)
    Optional<List<PrescripcionMedica>> findByCitaId(int id);
}
