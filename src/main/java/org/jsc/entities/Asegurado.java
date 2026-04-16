package org.jsc.entities;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

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

@Entity
public class Asegurado {

    @Id
    @Column(nullable = false)
    @GeneratedValue
    public UUID id;

    public Date fecha_ingreso;

    @Column(nullable = false, columnDefinition = "boolean default true")
    public Boolean activo;

    @Column(nullable = false, unique = true)
    public String nombres;

    public String identificacion;

    public String direccion;

    @Column(nullable = false, columnDefinition = "boolean default true")
    public Boolean esPersonaNatural;

    @JsonIgnoreProperties(value = "asegurado", allowSetters = true)
    @OneToMany(mappedBy = "asegurado", cascade = CascadeType.ALL)
    public List<Vehiculo> vehiculos;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    public Empresa empresa;

    public Asegurado() {
    }
    
}
