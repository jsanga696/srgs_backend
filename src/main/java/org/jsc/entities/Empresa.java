package org.jsc.entities;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    public List<Usuario> usuarios;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    public List<Usuario> clientes;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    public List<Asegurado> asegurados;

    public Empresa() {
    }
}