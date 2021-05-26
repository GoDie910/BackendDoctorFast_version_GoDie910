package com.doctorfast.org.repository;


import com.doctorfast.org.model.Diagnostico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosticoRepository extends JpaRepositoryImplementation<Diagnostico, Integer> {

    @Query(value = "select * from diagnostico where cita_id = ?1", nativeQuery = true)
    Optional<List<Diagnostico>> findByCitaId (int id);
}
