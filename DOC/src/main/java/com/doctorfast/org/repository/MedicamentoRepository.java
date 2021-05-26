package com.doctorfast.org.repository;

import com.doctorfast.org.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    @Query(value = "SELECT * FROM medicamento where medicamento_id = ?1",nativeQuery = true)
    Medicamento findById(int id);
}
