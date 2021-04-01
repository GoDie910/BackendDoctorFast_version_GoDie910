package com.doctorfast.org.repository;


import com.doctorfast.org.model.Cita;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepositoryImplementation<Cita, Integer> {


}
