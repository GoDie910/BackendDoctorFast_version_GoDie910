package com.doctorfast.org.repository;

import com.doctorfast.org.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad,Integer> {

    @Query(value = "SELECT * FROM especialidad e WHERE e.nombre = ?1",nativeQuery = true)
    Especialidad findByNombre(String nombre);

}
