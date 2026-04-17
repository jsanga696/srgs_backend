package org.jsc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String rol;

    private String nombres;

    private String identificacion;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Usuario() {
    }
}