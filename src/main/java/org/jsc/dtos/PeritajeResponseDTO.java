package org.jsc.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class PeritajeResponseDTO {

    public UUID id;
    public String codigo;
    public LocalDateTime fecha;
    public Boolean procede;

    public String nombres_asegurado;
    public String identificacion_asegurado;

    public String nombres_perito;
    public String identificacion_perito;

    public String marca_vehiculo;
    public String modelo_vehiculo;
    public String anio_vehiculo;
    public String chasis_vehiculo;

    public String detalles_peritaje;

    public PeritajeResponseDTO(){}
    
}
