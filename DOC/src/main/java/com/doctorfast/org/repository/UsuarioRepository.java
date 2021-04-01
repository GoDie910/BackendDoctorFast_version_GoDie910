package com.doctorfast.org.repository;

import com.doctorfast.org.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query(value = "SELECT * FROM usuario u WHERE u.nombre_usuario = ?1",nativeQuery = true)
    Usuario findByUserName(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario SET password=?4 WHERE nombre_usuario = ?1 AND dni = ?2 AND correo=?3 ", nativeQuery = true)
    int findByUserNameDniEmail(String username, String dni, String correo,String password);
}
