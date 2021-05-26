package com.doctorfast.org.service;

import com.doctorfast.org.model.Doctor;
import com.doctorfast.org.requests.DoctorRating;
import com.doctorfast.org.requests.RatingRequest;
import com.doctorfast.org.requests.RatingResponse;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    RatingResponse calificarDoctor(RatingRequest rating) throws Exception;

    List<Doctor> listarDoctores () throws Exception;

    String calificacionpromedio(Integer id) throws Exception;

    Optional<Doctor> getOne(int id) throws  Exception;

    Doctor obtenerDoctorPerfil(int id) throws  Exception;

    Doctor edit(Doctor d) throws Exception;

    Boolean existsById(int id) throws Exception;

    Doctor modificarDoctor(Doctor doctor) throws  Exception;

    Long numeroDeDoctores();

    List<Doctor> listarDoctoresPorDistrito(String distrito) throws Exception;

    List<Doctor> listarDoctoresDisponibles() throws Exception;

    List<DoctorRating> listarDoctoresPorRanking(int rating) throws Exception;

    List<DoctorRating> listarDoctoresPorRankingMejores() throws Exception;

    int cambiarStatus(int id) throws Exception;

}
