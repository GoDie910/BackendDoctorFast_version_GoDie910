package com.doctorfast.org.repository;

import com.doctorfast.org.model.AreaSintoma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaSintomaRepository extends JpaRepository<AreaSintoma, Integer> {
}
