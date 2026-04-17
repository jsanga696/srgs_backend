package org.jsc.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Citacion {

    public String id;
    public String placa;
    public String documento;
    public LocalDateTime fechaEmision;
    public LocalDateTime fechaVencimiento;
    public String entidad;
    public String descripcion;
    public BigDecimal valor;
    public String tipo;
    
}