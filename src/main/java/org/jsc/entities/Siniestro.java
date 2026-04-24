package org.jsc.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Siniestro {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;

    private String codigo;

    private UUID id_asegurado;

    private String identificacion_asegurado;
    private String nombre_asegurado;

    private UUID id_vehiculo;
    private String placa;
    
    private String identificacion_contraparte;
    private String nombre_contraparte;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    private String ubicacion;

    private String detalles;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean personasHeridas;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean necesitaGrua;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean esPersonaNatural;

    private String estado;

    public Siniestro() {
    }

}
