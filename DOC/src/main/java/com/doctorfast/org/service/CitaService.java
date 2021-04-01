package com.doctorfast.org.service;

import com.doctorfast.org.model.Cita;

import java.util.List;

public interface CitaService {


    List<Cita> listarCitas() throws Exception;

    Long numeroDeCitas();


}
