package org.jsc.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeritajeDTO {
    
    private UUID id;

    private UUID id_siniestro;

    private String codigoSiniestro;

    private UUID idAsegurado;
    private String identificacion_asegurado;
    private String nombre_asegurado;

    private UUID idVehiculo;
    private String placaVehiculo;

    private Long idUsuarioPerito;
    private String identificacion_perito;
    private String nombre_perito;

    private LocalDateTime fecha;
    private String detalles;

    private Boolean procede;

}
