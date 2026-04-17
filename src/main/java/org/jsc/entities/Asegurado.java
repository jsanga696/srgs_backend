package org.jsc.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Asegurado {

    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;

    private Date fecha_ingreso;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean activo;

    @Column(nullable = false, unique = true)
    private String nombres;

    private String identificacion;

    private String pais;

    private String provincia;

    private String ciudad;

    private String direccion;

    private String telefono;

    private String celular;

    private String email;

    private LocalDateTime fecha_creacion;

    private String usuario_creacion;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean esPersonaNatural;

    @JsonIgnoreProperties(value = "asegurado", allowSetters = true)
    @OneToMany(mappedBy = "asegurado", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Asegurado() {
    }
    
}
