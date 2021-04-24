package com.doctorfast.org.repository;

import com.doctorfast.org.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    @Query(value = "SELECT * FROM rating u WHERE u.doctor_id = ?1",nativeQuery = true)
    List<Rating> findByDoctorId(Integer id);

    @Query(value = "SELECT * FROM rating u WHERE u.doctor_id = ?1 and u.paciente_id = ?2", nativeQuery = true)
    Optional<Rating> findByDoctorIdandPacienteId(Integer doctor_id, Integer paciente_id);

}
