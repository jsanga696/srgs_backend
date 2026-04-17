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

    private UUID id_asegurado;

    private UUID id_vehiculo;

    private Long id_usuario_perito;

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

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean activo;

    public Siniestro() {
    }

}
