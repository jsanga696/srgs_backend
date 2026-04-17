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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Peritaje {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_siniestro")
    private Siniestro siniestro;

    @ManyToOne
    @JoinColumn(name = "id_asegurado")
    private Asegurado asegurado;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario perito;

    private String codigo;

    private String ruta_archivos;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean procede;

}
