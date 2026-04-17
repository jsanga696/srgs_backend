package org.jsc.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CitacionVehiculo {
    
    @Id
    @Column(nullable = false)
    private String id;
    private String documento;
    private String fechaEmision;
    private String fechaVencimiento;
    private String entidad;
    private String descripcion;
    private Double valor;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    public Vehiculo vehiculo;

    public CitacionVehiculo(){}

}
