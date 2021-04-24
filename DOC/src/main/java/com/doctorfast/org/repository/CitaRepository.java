package com.doctorfast.org.repository;


import com.doctorfast.org.model.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepositoryImplementation<Cita, Integer> {

    @Query(value = "SELECT * FROM cita c inner join paciente p on c.paciente_id = p.paciente_id where c.fecha_cita = NOW() and p.paciente_id = ?1", nativeQuery = true)
    List<Cita> findEnCursoPaciente(int id);

    @Query(value = "SELECT * FROM cita c inner join doctor d on c.doctor_id = d.doctor_id where c.fecha_cita = NOW() and d.doctor_id = ?1", nativeQuery = true)
    List<Cita> findEnCursoDoctor(int id);

    @Query(value = "SELECT * FROM cita c inner join paciente p on c.paciente_id = p.paciente_id where c.fecha_cita < NOW() and p.paciente_id = ?1",nativeQuery = true)
    List<Cita> findHistorialPaciente(int id);

    @Query(value = "SELECT * FROM cita c inner join doctor d on c.doctor_id = d.doctor_id where c.fecha_cita < NOW() and d.doctor_id = ?1", nativeQuery = true)
    List<Cita> findHistorialDoctor(int id);
}
