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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
    name = "vehiculo",
    uniqueConstraints = @UniqueConstraint(columnNames = {"placa", "asegurado_id"})
)
public class Vehiculo {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;

    private String chasis;

    @Column(nullable = false)
    private String placa;

    private String marca;

    private String modelo;

    private String color;

    private String anio_fabricacion;

    private String fechaMatricula;

    private String fechaCaducidad;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "asegurado_id")
    @JsonIgnoreProperties("vehiculos")
    private Asegurado asegurado;

    @JsonIgnoreProperties(value = "vehiculo", allowSetters = true)
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<CitacionVehiculo> citaciones;

    public Vehiculo() {
    }

}
