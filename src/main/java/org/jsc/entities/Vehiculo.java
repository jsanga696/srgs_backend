package org.jsc.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vehiculo {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;

    private String chasis;

    private String placa;

    private String marca;

    private String modelo;

    private String color;

    private String anio_fabricacion;

    private String fechaMatricula;

    private String fechaCaducidad;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "asegurado_id")
    private Asegurado asegurado;

    @JsonIgnoreProperties(value = "vehiculo", allowSetters = true)
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<CitacionVehiculo> citaciones;

    public Vehiculo() {
    }

}
