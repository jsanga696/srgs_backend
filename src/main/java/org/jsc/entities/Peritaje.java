package org.jsc.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Peritaje {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "id_siniestro")
    public Siniestro siniestro;

    @ManyToOne
    @JoinColumn(name = "id_asegurado")
    public Asegurado asegurado;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    public Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    public Usuario perito;

    public String codigo;

    public String ruta_archivos;

    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime fecha;

    @Column(nullable = false, columnDefinition = "boolean default false")
    public Boolean procede;

}
