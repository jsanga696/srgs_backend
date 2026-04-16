package org.jsc.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vehiculo {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue
    public UUID id;

    public String chasis;

    public String marca;

    public String modelo;

    public String anio_fabricacion;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "asegurado_id")
    public Asegurado asegurado;

    public Vehiculo() {
    }

}
