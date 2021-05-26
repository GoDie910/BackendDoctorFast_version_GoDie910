package com.doctorfast.org.repository;

import com.doctorfast.org.model.Doctor;
import com.doctorfast.org.model.Especialidad;
import com.doctorfast.org.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    @Query(value = "select * from doctor inner join usuario on doctor.usuario_id = usuario.usuario_id  where usuario.distrito = ?1",nativeQuery = true)
    List<Doctor> findByDistrict(String distrito);

    @Query(value = "select * from doctor d where d.disponibilidad = true",nativeQuery = true)
    List<Doctor> findDisponible();

    @Query(value = "select * from doctor where usuario_id=?1",nativeQuery = true)
    Doctor findByUsuarioId(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE doctor SET disponibilidad = ?1 WHERE doctor_id = ?2", nativeQuery = true)
    int cambiarStatusDoctor(boolean status, Integer id);

}
