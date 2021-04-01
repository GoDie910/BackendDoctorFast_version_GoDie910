package com.doctorfast.org.requests;

import com.doctorfast.org.model.Usuario;
import lombok.Data;

@Data
public class RegistroDoctorRequest {

    private Usuario usuario;

    private String dniFoto;

    private String descripcion;
}
