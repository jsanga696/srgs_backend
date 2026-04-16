package org.jsc.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cliente {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String nombres;

    public String identificacion;

    public String direccion;

    @Column(nullable = false, columnDefinition = "boolean default true")
    public Boolean esPersonaNatural;

    @Column(nullable = false, columnDefinition = "boolean default true")
    public Boolean activo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    public Empresa empresa;

    public Cliente() {
    }

}
