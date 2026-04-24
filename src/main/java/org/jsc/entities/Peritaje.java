package org.jsc.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    private UUID id_siniestro;

    private UUID id_asegurado;

    private String identificacion_asegurado;
    private String nombre_asegurado;

    private UUID id_vehiculo;

    private String placa;

    private Long id_usuario_perito;

    private String identificacion_perito;
    private String nombre_perito;

    private String detalles;
    private String codigo;

    private String ruta_archivos;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean procede;

    @JsonIgnoreProperties(value = "peritaje", allowSetters = true)
    @OneToMany(mappedBy = "peritaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Archivo> archivos;
}
