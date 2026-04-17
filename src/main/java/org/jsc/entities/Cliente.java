package org.jsc.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {
    
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombres;

    private String identificacion;

    private String direccion;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean esPersonaNatural;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Cliente() {
    }

}
