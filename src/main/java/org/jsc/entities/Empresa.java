package org.jsc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String razon_social;

    private String ruc;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean activo;
    
    private String pais;

    private String provincia;

    private String ciudad;

    private String direccion;

    private String telefono;

    private String celular;

    private String email;

    private LocalDateTime fecha_creacion;

    private String usuario_creacion;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    private List<Usuario> clientes;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    private List<Asegurado> asegurados;

    public Empresa() {
    }
}