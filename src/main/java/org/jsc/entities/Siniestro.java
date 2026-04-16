package org.jsc.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Siniestro {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue
    public UUID id;

    public UUID id_asegurado;

    public UUID id_vehiculo;

    public Long id_usuario_perito;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime fecha;

    public String ubicacion;

    public String detalles;

    @Column(nullable = false, columnDefinition = "boolean default false")
    public Boolean personasHeridas;

    @Column(nullable = false, columnDefinition = "boolean default false")
    public Boolean necesitaGrua;

    @Column(nullable = false, columnDefinition = "boolean default true")
    public Boolean esPersonaNatural;

    @Column(nullable = false, columnDefinition = "boolean default true")
    public Boolean activo;

    public Siniestro() {
    }

}
