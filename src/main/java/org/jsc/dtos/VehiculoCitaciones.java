package org.jsc.dtos;

import java.util.List;

import lombok.Data;

@Data
public class VehiculoCitaciones {
    
    private String placa;
    private String marca;
    private String color;
    private String modelo;
    private String anio_fabricacion;
    private String fechaMatricula;
    private String fechaCaducidad;
    private List<Citacion> citaciones;

    public VehiculoCitaciones(){

    }

}
