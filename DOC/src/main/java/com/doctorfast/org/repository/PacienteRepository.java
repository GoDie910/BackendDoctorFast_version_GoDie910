package com.doctorfast.org.repository;

import com.doctorfast.org.model.Doctor;
import com.doctorfast.org.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {

    @Query(value = "select * from paciente where usuario_id=?1",nativeQuery = true)
    Paciente findByUsuarioId(int id);
}
