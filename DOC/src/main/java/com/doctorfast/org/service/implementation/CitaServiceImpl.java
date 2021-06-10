package com.doctorfast.org.service.implementation;


import com.doctorfast.org.model.*;
import com.doctorfast.org.repository.*;
import com.doctorfast.org.requests.*;
import com.doctorfast.org.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    private CitaRepository citaRepository;
    private PacienteRepository pacienteRepository;
    private DoctorRepository doctorRepository;
    private PrescripcionMedicaRepository prescripcionMedicaRepository;
    private AreaSintomaRepository areaSintomaRepository;
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    public CitaServiceImpl(
            CitaRepository citaRepository,
            PacienteRepository pacienteRepository,
            DoctorRepository doctorRepository,
            PrescripcionMedicaRepository prescripcionMedicaRepository,
            AreaSintomaRepository areaSintomaRepository,
            DiagnosticoRepository diagnosticoRepository){

        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
        this.prescripcionMedicaRepository = prescripcionMedicaRepository;
        this.areaSintomaRepository = areaSintomaRepository;
        this.diagnosticoRepository = diagnosticoRepository;
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
    public List<Cita> listarHistorialCitasMedico(int id) {
        Doctor doctor_aux = doctorRepository.findByUsuarioId(id);
        return citaRepository.findHistorialDoctor(doctor_aux.getIdDoctor());
    }

    @Override
    public List<Cita> listarCitasDisponiblesPaciente(int id) {
        Paciente paciente_aux = pacienteRepository.findByUsuarioId(id);
        return citaRepository.findDisponiblesPaciente(paciente_aux.getIdPaciente());
    }

    @Override
    public List<Cita> listarCitasPendientesPaciente(int id) {
        Paciente paciente_aux = pacienteRepository.findByUsuarioId(id);
        return citaRepository.findPendientesPaciente(paciente_aux.getIdPaciente());
    }

    @Override
    public List<Cita> listarHistorialCitasPaciente(int id) {
        Paciente paciente_aux = pacienteRepository.findByUsuarioId(id);
        return citaRepository.findHistorialPaciente(paciente_aux.getIdPaciente());
    }

    @Override
    public List<HistoriaMedicaResponse> HistorialMedicoPaciente(int id) throws Exception {

        //obtener el paciente a partir de su usuario_id
        Paciente paciente = pacienteRepository.findByUsuarioId(id);
        //obtener la lista de citas del paciente de su Historia Medica
        List<Cita> citaList = citaRepository.findHistoriaMedicaPaciente(paciente.getIdPaciente());

        //inicializamos la lista de Historia Medica a regresar al front
        List<HistoriaMedicaResponse> historiaMedicaResponseList = new ArrayList<HistoriaMedicaResponse>();

        //las variables temporales para almacenar UNA cita del historial medico (lo hacemos para formatear)
        HistoriaMedicaResponse historiaMedicaResponse = new HistoriaMedicaResponse();
        HistoriaMedicaCitaResponse historiaMedicaCitaResponse = new HistoriaMedicaCitaResponse();
        Optional<List<Diagnostico>> listDiagnosticoCita = Optional.of(new ArrayList<Diagnostico>());
        Optional<List<PrescripcionMedica>> listPrescripcionMedicaCita = Optional.of(new ArrayList<PrescripcionMedica>());

        //formateo de Cita -> HistoriaMedicaResponse
        for (int i = 0; i < citaList.size(); i++){
            historiaMedicaCitaResponse.setIdCita(citaList.get(i).getIdCita());
            historiaMedicaCitaResponse.setStatus(citaList.get(i).getStatus());
            historiaMedicaCitaResponse.setDescripcion(citaList.get(i).getDescripcion());
            historiaMedicaCitaResponse.setHoraInicio(citaList.get(i).getHoraInicio());
            historiaMedicaCitaResponse.setHoraFin(citaList.get(i).getHoraFin());
            historiaMedicaCitaResponse.setCodigoCita(citaList.get(i).getCodigoCita());
            historiaMedicaCitaResponse.setFechaCita(citaList.get(i).getFechaCita());
            historiaMedicaCitaResponse.setPaciente_nombre(citaList.get(i).getPaciente().getUsuario().getNombre());
            historiaMedicaCitaResponse.setAreaSintoma(citaList.get(i).getAreaSintoma());

            HistoriaMedicaDoctorResponse doctorResponse = new HistoriaMedicaDoctorResponse();
            doctorResponse.setIdDoctor(citaList.get(i).getDoctor().getIdDoctor());
            doctorResponse.setDoctor_nombre(citaList.get(i).getDoctor().getUsuario().getNombre());
            doctorResponse.setDoctor_apellido_paterno(citaList.get(i).getDoctor().getUsuario().getApellidoPaterno());
            doctorResponse.setDoctor_apellido_materno(citaList.get(i).getDoctor().getUsuario().getApellidoMaterno());
            doctorResponse.setDoctor_especialidad(citaList.get(i).getDoctor().getEspecialidad());
            doctorResponse.setDoctor_centroLabor(citaList.get(i).getDoctor().getCentroLabor());

            historiaMedicaCitaResponse.setDoctor(doctorResponse);

            historiaMedicaResponse.setCita(historiaMedicaCitaResponse);
            historiaMedicaCitaResponse = new HistoriaMedicaCitaResponse();
            //-----------------------------
            listDiagnosticoCita = diagnosticoRepository.findByCitaId(citaList.get(i).getIdCita());
            if(listDiagnosticoCita.isPresent()){
                HistoriaMedicaDiagnosticoResponse historiaMedicaDiagnosticoResponse = new HistoriaMedicaDiagnosticoResponse();
                List<HistoriaMedicaDiagnosticoResponse> historiaMedicaDiagnosticoResponseList = new ArrayList<>();
                for(int j = 0; j < listDiagnosticoCita.get().size(); j++){
                    historiaMedicaDiagnosticoResponse.setIdDiagnostico(listDiagnosticoCita.get().get(j).getIdDiagnostico());
                    historiaMedicaDiagnosticoResponse.setNombre(listDiagnosticoCita.get().get(j).getNombre());
                    historiaMedicaDiagnosticoResponse.setDescripcion(listDiagnosticoCita.get().get(j).getDescripcion());

                    historiaMedicaDiagnosticoResponseList.add(historiaMedicaDiagnosticoResponse);
                    historiaMedicaDiagnosticoResponse = new HistoriaMedicaDiagnosticoResponse();
                }
                historiaMedicaResponse.setDiagnosticos(Optional.of(historiaMedicaDiagnosticoResponseList));
            }
            //-----------------------------
            listPrescripcionMedicaCita = prescripcionMedicaRepository.findByCitaId(citaList.get(i).getIdCita());
            if(listPrescripcionMedicaCita.isPresent()){
                HistoriaMedicaPrescripcionMedicaResponse historiaMedicaPrescripcionMedicaResponse = new HistoriaMedicaPrescripcionMedicaResponse();
                List<HistoriaMedicaPrescripcionMedicaResponse> historiaMedicaPrescripcionMedicaResponseList = new ArrayList<>();
                for(int j = 0; j < listPrescripcionMedicaCita.get().size(); j++){
                    historiaMedicaPrescripcionMedicaResponse.setIdPrescripcionMedica(listPrescripcionMedicaCita.get().get(j).getIdPrescripcionMedica());
                    historiaMedicaPrescripcionMedicaResponse.setCantidad(listPrescripcionMedicaCita.get().get(j).getCantidad());
                    historiaMedicaPrescripcionMedicaResponse.setFechaInicio(listPrescripcionMedicaCita.get().get(j).getFechaInicio());
                    historiaMedicaPrescripcionMedicaResponse.setDescripcion(listPrescripcionMedicaCita.get().get(j).getDescripcion());
                    historiaMedicaPrescripcionMedicaResponse.setMedicamento(listPrescripcionMedicaCita.get().get(j).getMedicamento());

                    historiaMedicaPrescripcionMedicaResponseList.add(historiaMedicaPrescripcionMedicaResponse);
                    historiaMedicaPrescripcionMedicaResponse = new HistoriaMedicaPrescripcionMedicaResponse();
                }
                historiaMedicaResponse.setPrescripcionesMedicas(Optional.of(historiaMedicaPrescripcionMedicaResponseList));
            }
            //-----------------------------
            historiaMedicaResponseList.add(historiaMedicaResponse);
            historiaMedicaResponse = new HistoriaMedicaResponse();
        }
        return historiaMedicaResponseList;
    }

    @Override
    public List<HistoriaMedicaResponse> HistorialMedicoPacienteVistaDoctor(int id) throws Exception {
        //obtener el paciente a partir de su usuario_id
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        //obtener la lista de citas del paciente de su Historia Medica
        List<Cita> citaList = citaRepository.findHistoriaMedicaPaciente(paciente.get().getIdPaciente());

        //inicializamos la lista de Historia Medica a regresar al front
        List<HistoriaMedicaResponse> historiaMedicaResponseList = new ArrayList<HistoriaMedicaResponse>();

        //las variables temporales para almacenar UNA cita del historial medico (lo hacemos para formatear)
        HistoriaMedicaResponse historiaMedicaResponse = new HistoriaMedicaResponse();
        HistoriaMedicaCitaResponse historiaMedicaCitaResponse = new HistoriaMedicaCitaResponse();
        Optional<List<Diagnostico>> listDiagnosticoCita = Optional.of(new ArrayList<Diagnostico>());
        Optional<List<PrescripcionMedica>> listPrescripcionMedicaCita = Optional.of(new ArrayList<PrescripcionMedica>());

        //formateo de Cita -> HistoriaMedicaResponse
        for (int i = 0; i < citaList.size(); i++){
            historiaMedicaCitaResponse.setIdCita(citaList.get(i).getIdCita());
            historiaMedicaCitaResponse.setStatus(citaList.get(i).getStatus());
            historiaMedicaCitaResponse.setDescripcion(citaList.get(i).getDescripcion());
            historiaMedicaCitaResponse.setHoraInicio(citaList.get(i).getHoraInicio());
            historiaMedicaCitaResponse.setHoraFin(citaList.get(i).getHoraFin());
            historiaMedicaCitaResponse.setCodigoCita(citaList.get(i).getCodigoCita());
            historiaMedicaCitaResponse.setFechaCita(citaList.get(i).getFechaCita());
            historiaMedicaCitaResponse.setPaciente_nombre(citaList.get(i).getPaciente().getUsuario().getNombre());
            historiaMedicaCitaResponse.setAreaSintoma(citaList.get(i).getAreaSintoma());

            HistoriaMedicaDoctorResponse doctorResponse = new HistoriaMedicaDoctorResponse();
            doctorResponse.setIdDoctor(citaList.get(i).getDoctor().getIdDoctor());
            doctorResponse.setDoctor_nombre(citaList.get(i).getDoctor().getUsuario().getNombre());
            doctorResponse.setDoctor_apellido_paterno(citaList.get(i).getDoctor().getUsuario().getApellidoPaterno());
            doctorResponse.setDoctor_apellido_materno(citaList.get(i).getDoctor().getUsuario().getApellidoMaterno());
            doctorResponse.setDoctor_especialidad(citaList.get(i).getDoctor().getEspecialidad());
            doctorResponse.setDoctor_centroLabor(citaList.get(i).getDoctor().getCentroLabor());

            historiaMedicaCitaResponse.setDoctor(doctorResponse);

            historiaMedicaResponse.setCita(historiaMedicaCitaResponse);
            historiaMedicaCitaResponse = new HistoriaMedicaCitaResponse();
            //-----------------------------
            listDiagnosticoCita = diagnosticoRepository.findByCitaId(citaList.get(i).getIdCita());
            if(listDiagnosticoCita.isPresent()){
                HistoriaMedicaDiagnosticoResponse historiaMedicaDiagnosticoResponse = new HistoriaMedicaDiagnosticoResponse();
                List<HistoriaMedicaDiagnosticoResponse> historiaMedicaDiagnosticoResponseList = new ArrayList<>();
                for(int j = 0; j < listDiagnosticoCita.get().size(); j++){
                    historiaMedicaDiagnosticoResponse.setIdDiagnostico(listDiagnosticoCita.get().get(j).getIdDiagnostico());
                    historiaMedicaDiagnosticoResponse.setNombre(listDiagnosticoCita.get().get(j).getNombre());
                    historiaMedicaDiagnosticoResponse.setDescripcion(listDiagnosticoCita.get().get(j).getDescripcion());

                    historiaMedicaDiagnosticoResponseList.add(historiaMedicaDiagnosticoResponse);
                    historiaMedicaDiagnosticoResponse = new HistoriaMedicaDiagnosticoResponse();
                }
                historiaMedicaResponse.setDiagnosticos(Optional.of(historiaMedicaDiagnosticoResponseList));
            }
            //-----------------------------
            listPrescripcionMedicaCita = prescripcionMedicaRepository.findByCitaId(citaList.get(i).getIdCita());
            if(listPrescripcionMedicaCita.isPresent()){
                HistoriaMedicaPrescripcionMedicaResponse historiaMedicaPrescripcionMedicaResponse = new HistoriaMedicaPrescripcionMedicaResponse();
                List<HistoriaMedicaPrescripcionMedicaResponse> historiaMedicaPrescripcionMedicaResponseList = new ArrayList<>();
                for(int j = 0; j < listPrescripcionMedicaCita.get().size(); j++){
                    historiaMedicaPrescripcionMedicaResponse.setIdPrescripcionMedica(listPrescripcionMedicaCita.get().get(j).getIdPrescripcionMedica());
                    historiaMedicaPrescripcionMedicaResponse.setCantidad(listPrescripcionMedicaCita.get().get(j).getCantidad());
                    historiaMedicaPrescripcionMedicaResponse.setFechaInicio(listPrescripcionMedicaCita.get().get(j).getFechaInicio());
                    historiaMedicaPrescripcionMedicaResponse.setDescripcion(listPrescripcionMedicaCita.get().get(j).getDescripcion());
                    historiaMedicaPrescripcionMedicaResponse.setMedicamento(listPrescripcionMedicaCita.get().get(j).getMedicamento());

                    historiaMedicaPrescripcionMedicaResponseList.add(historiaMedicaPrescripcionMedicaResponse);
                    historiaMedicaPrescripcionMedicaResponse = new HistoriaMedicaPrescripcionMedicaResponse();
                }
                historiaMedicaResponse.setPrescripcionesMedicas(Optional.of(historiaMedicaPrescripcionMedicaResponseList));
            }
            //-----------------------------
            //-----------------------------
            historiaMedicaResponseList.add(historiaMedicaResponse);
            historiaMedicaResponse = new HistoriaMedicaResponse();
        }
        return historiaMedicaResponseList;
    }

    @Override
    public List<Cita> listarCitasDisponiblesDoctor(int id) {
        Doctor doctor_aux = doctorRepository.findByUsuarioId(id);
        return citaRepository.findDisponiblesDoctor(doctor_aux.getIdDoctor());
    }

    @Override
    public List<Cita> listarCitasPendientesDoctor( int id) {
        Doctor doctor_aux = doctorRepository.findByUsuarioId(id);
        return citaRepository.findPendientesDoctor(doctor_aux.getIdDoctor());
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

    @Override
    public int aceptarCitaDoctor(int usuario_id, int cita_id){
        Doctor doctor_aux = doctorRepository.findByUsuarioId(usuario_id);
        return citaRepository.aceptarCitaDoctor(doctor_aux.getIdDoctor(), cita_id);
    }

    @Override
    public int cancelarCitaDoctor(int usuario_id, int cita_id){
        Doctor doctor_aux = doctorRepository.findByUsuarioId(usuario_id);
        return citaRepository.cancelarCitaDoctor(doctor_aux.getIdDoctor(), cita_id);
    }

    @Override
    public Cita realizarCita(CitaRealizarRequest citaRealizar) throws Exception {
        Cita cita = citaRepository.findById(citaRealizar.getCita_id());
        cita.setHoraFin(citaRealizar.getHoraFin());
        cita.setHoraInicio(citaRealizar.getHoraInicio());
        cita.setStatus(4);
        return citaRepository.save(cita);
    }
}
