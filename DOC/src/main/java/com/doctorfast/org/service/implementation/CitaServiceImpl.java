package com.doctorfast.org.service.implementation;


import com.doctorfast.org.model.*;
import com.doctorfast.org.repository.*;
import com.doctorfast.org.requests.CitaCreateRequest;
import com.doctorfast.org.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    private CitaRepository citaRepository;
    private PacienteRepository pacienteRepository;
    private DoctorRepository doctorRepository;
    private PrescripcionMedicaRepository prescripcionMedicaRepository;
    private AreaSintomaRepository areaSintomaRepository;

    @Autowired
    public CitaServiceImpl(
            CitaRepository citaRepository,
            PacienteRepository pacienteRepository,
            DoctorRepository doctorRepository,
            PrescripcionMedicaRepository prescripcionMedicaRepository,
            AreaSintomaRepository areaSintomaRepository){

        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
        this.prescripcionMedicaRepository = prescripcionMedicaRepository;
        this.areaSintomaRepository = areaSintomaRepository;
    }

    @Override
    public List<Cita> listarCitas() throws Exception {
        return citaRepository.findAll();
    }

    @Override
    public Long numeroDeCitas() {
        return citaRepository.count();
    }

    @Override
    public List<Cita> listarHistorialCitasPaciente(int id) {
        Paciente paciente_aux = pacienteRepository.findByUsuarioId(id);
        return citaRepository.findHistorialPaciente(paciente_aux.getIdPaciente());
    }

    @Override
    public List<Cita> listarHistorialCitasMedico(int id) {
        Doctor doctor_aux = doctorRepository.findByUsuarioId(id);
        return citaRepository.findHistorialDoctor(doctor_aux.getIdDoctor());
    }

    @Override
    public List<Cita> listarCitasEnCursoPaciente(int id) {
        Paciente paciente_aux = pacienteRepository.findByUsuarioId(id);
        return citaRepository.findEnCursoPaciente(paciente_aux.getIdPaciente());
    }

    @Override
    public List<Cita> listarCitasEnCursoDoctor(int id) {
        Doctor doctor_aux = doctorRepository.findByUsuarioId(id);
        return citaRepository.findEnCursoDoctor(doctor_aux.getIdDoctor());
    }

    @Override
    public Cita crearCitaPaciente(CitaCreateRequest cita_request) {
        Paciente paciente_aux = pacienteRepository.findByUsuarioId(cita_request.getPaciente_usuario_id());
        Optional<Doctor> doctor_aux = doctorRepository.findById(cita_request.getDoctor_id());
        Optional<AreaSintoma> areaSintoma_aux = areaSintomaRepository.findById(cita_request.getAreaSintoma_id());

        if(paciente_aux != null && doctor_aux.isPresent() && areaSintoma_aux.isPresent())
        {

            Cita cita_aux = new Cita();

            cita_aux.setStatus(1);
            cita_aux.setDescripcion(cita_request.getDescripcion());
            cita_aux.setHoraInicio(cita_request.getHoraInicio());
            cita_aux.setHoraFin(null);
            cita_aux.setFechaCita(cita_request.getFechaCita());
            cita_aux.setAreaSintoma(areaSintoma_aux.get());
            cita_aux.setDoctor(doctor_aux.get());
            cita_aux.setPaciente(paciente_aux);
            cita_aux.setPrescripcionMedica(prescripcionMedicaRepository.findById(1).get());
            cita_aux.setCodigoCita("000");

            return citaRepository.save(cita_aux);
        }

        Cita cita_Error = new Cita();
        cita_Error.setDescripcion("Errooorrrrr");

        if(paciente_aux == null){cita_Error.setDescripcion(cita_Error.getDescripcion() + " paciente ");}
        if(!doctor_aux.isPresent()){cita_Error.setDescripcion(cita_Error.getDescripcion() + " doctor ");}
        if(!areaSintoma_aux.isPresent()){cita_Error.setDescripcion(cita_Error.getDescripcion() + " areaSintoma ");}

        return cita_Error;

    }
}
