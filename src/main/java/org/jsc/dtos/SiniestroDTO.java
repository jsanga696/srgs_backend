package org.jsc.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class SiniestroDTO {
    
    private UUID id;

    private UUID idAsegurado;
    private String nombreAsegurado;

    private UUID idVehiculo;
    private String placaVehiculo;

    private Long idUsuarioPerito;

    private LocalDateTime fecha;
    private String ubicacion;
    private String detalles;

    private Boolean personasHeridas;
    private Boolean necesitaGrua;
    private Boolean activo;
     
}
