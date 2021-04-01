package com.doctorfast.org.repository;

import com.doctorfast.org.model.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol,Integer> {

    @Query(value = "SELECT * FROM usuario_rol u WHERE u.usuario_id = ?1",nativeQuery = true)
    UsuarioRol findByUserId(Integer id);
}
