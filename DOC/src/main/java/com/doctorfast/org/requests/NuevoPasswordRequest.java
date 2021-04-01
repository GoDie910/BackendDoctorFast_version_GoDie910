package com.doctorfast.org.requests;

import lombok.Data;

@Data
public class NuevoPasswordRequest {

    private String nombreUsuario;

    private String correo;

    private String dni;

    private String nuevoPassword;
}
