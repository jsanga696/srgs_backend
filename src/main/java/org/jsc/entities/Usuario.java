package org.jsc.entities;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String username;

    public String password;

    public String rol;

    public String nombres;

    public String identificacion;

    @Column(nullable = false, columnDefinition = "boolean default true")
    public Boolean activo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    public Empresa empresa;

    public Usuario() {
    }
}