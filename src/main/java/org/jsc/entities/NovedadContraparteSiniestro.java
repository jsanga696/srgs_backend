package org.jsc.entities;

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
public class NovedadContraparteSiniestro {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;
    
    private UUID id_siniestro;

    private String lugar;
    private String fecha;
    private String hora;
    private String nro_documento;
    private String delito;
    private String unidad;
    private String fiscalia;

    private String identificacion_denunciante;
    private String nombres_denunciante;

    private String identificacion_denunciado;
    private String nombres_denunciado;

}
