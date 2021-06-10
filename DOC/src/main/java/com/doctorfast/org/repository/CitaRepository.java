package com.doctorfast.org.repository;


import com.doctorfast.org.model.Cita;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepositoryImplementation<Cita, Integer> {

    //CITA
    @Query(value = "SELECT * FROM cita where cita_id = ?1", nativeQuery = true)
    Cita findById(int id);

    //PACIENTE
    @Query(value = "SELECT * FROM cita where fecha_cita >= current_Date and status = 2 and paciente_id = ?1", nativeQuery = true)
    List<Cita> findDisponiblesPaciente(int id);

    @Query(value = "SELECT * FROM cita where fecha_cita >= current_Date and status in (1,3) and paciente_id = ?1", nativeQuery = true)
    List<Cita> findPendientesPaciente(int id);

    @Query(value = "SELECT * FROM cita where fecha_cita <= current_Date and hora_fin <= current_time(0) and status in (2,3,4) and paciente_id = ?1", nativeQuery = true)
    List<Cita> findHistorialPaciente (int id);

    @Query(value = "SELECT * FROM cita where fecha_cita <= current_Date and hora_fin <= current_time(0) and status = 4 and paciente_id = ?1", nativeQuery = true)
    List<Cita> findHistoriaMedicaPaciente(int id);



    //DOCTOR
    @Query(value = "SELECT * FROM cita where fecha_cita >= current_Date and doctor_id = ?1 and status = 1", nativeQuery = true)
    List<Cita> findPendientesDoctor(int id);

    @Query(value = "SELECT * FROM cita where fecha_cita >= current_Date and doctor_id = ?1 and status = 2", nativeQuery = true)
    List<Cita> findDisponiblesDoctor(int id);

    @Query(value = "SELECT * FROM cita where fecha_cita <= current_Date and hora_fin <= current_time(0) and doctor_id = ?1", nativeQuery = true)
    List<Cita> findHistorialDoctor(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.cita SET status = 2 WHERE doctor_id = ?1 and cita_id = ?2", nativeQuery = true)
    int aceptarCitaDoctor(int doctor_id, int cita_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.cita SET status = 3 WHERE doctor_id = ?1 and cita_id = ?2", nativeQuery = true)
    int cancelarCitaDoctor(int doctor_id, int cita_id);

}
