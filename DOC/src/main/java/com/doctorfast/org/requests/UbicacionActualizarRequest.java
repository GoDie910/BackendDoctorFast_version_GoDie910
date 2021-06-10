package com.doctorfast.org.requests;

import lombok.Data;

@Data
public class UbicacionActualizarRequest {

    private Integer usuario_id;

    private String longitud;

    private String latitud;
}
