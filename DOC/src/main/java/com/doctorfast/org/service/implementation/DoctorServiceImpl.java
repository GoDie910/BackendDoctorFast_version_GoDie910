package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.*;
import com.doctorfast.org.repository.DoctorRepository;
import com.doctorfast.org.repository.PacienteRepository;
import com.doctorfast.org.repository.RatingRepository;
import com.doctorfast.org.repository.UsuarioRepository;
import com.doctorfast.org.requests.DoctorRating;
import com.doctorfast.org.requests.RatingRequest;
import com.doctorfast.org.requests.RatingResponse;
import com.doctorfast.org.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class DoctorServiceImpl implements DoctorService {

    private RatingRepository ratingRepository;
    private DoctorRepository doctorRepository;
    private PacienteRepository pacienteRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public DoctorServiceImpl(
            RatingRepository ratingRepository,
            DoctorRepository doctorRepository,
            PacienteRepository pacienteRepository,
            UsuarioRepository usuarioRepository){
        this.ratingRepository = ratingRepository;
        this.doctorRepository = doctorRepository;
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public RatingResponse calificarDoctor(RatingRequest rating) throws Exception {
        Doctor doctor = doctorRepository.getOne(rating.getDoctorId());
        Paciente paciente = pacienteRepository.getOne(rating.getPacienteId());

        RatingResponse ratingResponse = new RatingResponse();

        Optional<Rating> rating_optional = ratingRepository.findByDoctorIdandPacienteId(rating.getDoctorId(), rating.getPacienteId());

        if(rating_optional.isPresent()){
            Rating rating_existente = rating_optional.get();
            rating_existente.setCalificacion(rating.getCalificacion());
            ratingRepository.save(rating_existente);

            ratingResponse.setIdRating(rating_existente.getIdRating());
            ratingResponse.setCalificacion(rating_existente.getCalificacion());
            ratingResponse.setDoctorId(rating.getDoctorId());
            ratingResponse.setPacienteId(rating.getPacienteId());
        }else{
            Rating calificacion = new Rating();
            calificacion.setCalificacion(rating.getCalificacion());
            calificacion.setDoctor(doctor);
            calificacion.setPaciente(paciente);
            ratingRepository.save(calificacion);

            ratingResponse.setIdRating(calificacion.getIdRating());
            ratingResponse.setCalificacion(calificacion.getCalificacion());
            ratingResponse.setDoctorId(rating.getDoctorId());
            ratingResponse.setPacienteId(rating.getPacienteId());
        }

        return ratingResponse;
    }

    @Override
    public List<Doctor> listarDoctores() throws Exception {
        return doctorRepository.findAll();
    }

    @Override
    public String calificacionpromedio(Integer id) throws Exception {
        List<String> calificaciones = new ArrayList<>();
        double promedio = 0.0;

        List<Rating> ratings = ratingRepository.findByDoctorId(id);

        for(Rating r:ratings){
            calificaciones.add(r.getCalificacion());
        }
        for (String c:calificaciones){
            promedio = promedio + Double.parseDouble(c);
        }
        promedio = promedio/calificaciones.size();

        return Double.toString(promedio);
    }

    @Override
    public Optional<Doctor> getOne(int id) throws Exception{
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor obtenerDoctorPerfil(int id) throws Exception{
        return doctorRepository.findByUsuarioId(id);
    }

    @Override
    public Doctor edit(Doctor d) throws Exception {
        return doctorRepository.save(d);
    }

    @Override
    public Boolean existsById(int id) throws Exception {
        return doctorRepository.existsById(id);
    }

    @Override
    public Doctor modificarDoctor(Doctor doctor) throws Exception {
        Doctor doctor_bd = doctorRepository.getOne(doctor.getIdDoctor());

        doctor_bd.setDniFoto(doctor.getDniFoto());
        doctor_bd.setDisponibilidad(doctor.getDisponibilidad());
        doctor_bd.setHabilitado(doctor.getHabilitado());
        doctor_bd.setNumeroColegiatura(doctor.getNumeroColegiatura());
        doctor_bd.setCentroLabor(doctor.getCentroLabor());
        doctor_bd.setTarifa(doctor.getTarifa());
        doctor_bd.setEspecialidad(doctor.getEspecialidad());

        doctor_bd.getUsuario().setNombreUsuario(doctor.getUsuario().getNombreUsuario());
        doctor_bd.getUsuario().setPassword(doctor.getUsuario().getPassword());
        doctor_bd.getUsuario().setNombre(doctor.getUsuario().getNombre());
        doctor_bd.getUsuario().setApellidoPaterno(doctor.getUsuario().getApellidoPaterno());
        doctor_bd.getUsuario().setApellidoMaterno(doctor.getUsuario().getApellidoMaterno());
        doctor_bd.getUsuario().setDni(doctor.getUsuario().getDni());
        doctor_bd.getUsuario().setCorreo(doctor.getUsuario().getCorreo());
        doctor_bd.getUsuario().setCelular(doctor.getUsuario().getCelular());
        doctor_bd.getUsuario().setFechaNacimiento(doctor.getUsuario().getFechaNacimiento());
        doctor_bd.getUsuario().setDistrito(doctor.getUsuario().getDistrito());
        doctor_bd.getUsuario().setDireccion(doctor.getUsuario().getDireccion());

        return doctorRepository.save(doctor_bd);
    }

    @Override
    public Long numeroDeDoctores() {
        return doctorRepository.count();
    }

    @Override
    public List<Doctor> listarDoctoresPorDistrito(String distrito) throws Exception {
        return doctorRepository.findByDistrict(distrito);
    }

    @Override
    public List<Doctor> listarDoctoresDisponibles() throws Exception {
        return doctorRepository.findDisponible();
    }

    @Override
    public List<DoctorRating> listarDoctoresPorRanking(int rating) throws Exception {
        List<Doctor> listDoctor = doctorRepository.findAll();
        ArrayList<DoctorRating> listDoctorRating = new ArrayList<>();
        DoctorRating doctorRating = new DoctorRating();

        for(Doctor d:listDoctor){
            doctorRating.setRating(Double.parseDouble(calificacionpromedio(d.getIdDoctor())));
            if(doctorRating.getRating().isNaN()){
                doctorRating.setRating(0.0);
            }
            if(doctorRating.getRating() >= rating) {
                doctorRating.setIdDoctor(d.getIdDoctor());
                doctorRating.setDniFoto(d.getDniFoto());
                doctorRating.setDisponibilidad(d.getDisponibilidad());
                doctorRating.setHabilitado(d.getHabilitado());
                doctorRating.setUsuario(d.getUsuario());
                doctorRating.setEspecialidad(d.getEspecialidad());
                doctorRating.setNumeroColegiatura(d.getNumeroColegiatura());
                doctorRating.setCentroLabor(d.getCentroLabor());
                doctorRating.setTarifa(d.getTarifa());
                listDoctorRating.add(doctorRating);
                doctorRating = new DoctorRating();
            }
        }

        listDoctorRating.sort(Comparator.comparing(DoctorRating::getRating).reversed());

        return listDoctorRating;
    }

    @Override
    public List<DoctorRating> listarDoctoresPorRankingMejores() throws Exception {
        List<Doctor> listDoctor = doctorRepository.findAll();
        ArrayList<DoctorRating> listDoctorRating = new ArrayList<>();
        DoctorRating doctorRating = new DoctorRating();

        for(Doctor d:listDoctor){
            doctorRating.setRating(Double.parseDouble(calificacionpromedio(d.getIdDoctor())));
            if(doctorRating.getRating().isNaN()){
                doctorRating.setRating(0.0);
            }
            doctorRating.setIdDoctor(d.getIdDoctor());
            doctorRating.setDniFoto(d.getDniFoto());
            doctorRating.setDisponibilidad(d.getDisponibilidad());
            doctorRating.setHabilitado(d.getHabilitado());
            doctorRating.setUsuario(d.getUsuario());
            doctorRating.setEspecialidad(d.getEspecialidad());
            doctorRating.setNumeroColegiatura(d.getNumeroColegiatura());
            doctorRating.setCentroLabor(d.getCentroLabor());
            doctorRating.setTarifa(d.getTarifa());
            listDoctorRating.add(doctorRating);
            doctorRating = new DoctorRating();
        }

        listDoctorRating.sort(Comparator.comparing(DoctorRating::getRating).reversed());

        if(listDoctorRating.size() > 5){
            ArrayList<DoctorRating> listDoctorRatingTop5 = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                listDoctorRatingTop5.add(listDoctorRating.get(i));
            }
            return listDoctorRatingTop5;
        }else{
            return listDoctorRating;
        }
    }

}
