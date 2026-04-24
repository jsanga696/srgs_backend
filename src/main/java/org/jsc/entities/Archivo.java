package org.jsc.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Archivo {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;

    public String nombre;

    @ManyToOne
    public Peritaje peritaje;
    
}
