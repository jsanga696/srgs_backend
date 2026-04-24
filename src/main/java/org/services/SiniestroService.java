package org.services;

import java.util.List;
import java.util.UUID;

import org.jsc.dtos.PageResponse;
import org.jsc.dtos.SiniestroDTO;
import org.jsc.entities.Siniestro;
import org.jsc.repositories.SiniestroRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class SiniestroService {
    
    @Inject
    SiniestroRepository repository;

    public Siniestro buscarPorId(UUID id) {

        Siniestro siniestro = repository.buscarPorId(id);

        if (siniestro == null) {
            throw new RuntimeException("Siniestro no encontrado");
        }

        return siniestro;
    }

    public PageResponse<Siniestro> listar(int page, int size, String nombreAsegurado, String codigo, String estado) {
        return repository.listar(page, size, nombreAsegurado, codigo, estado);
    }

    @Transactional
    public Siniestro crear(Siniestro siniestro) {
        return repository.guardar(siniestro);
    }

    @Transactional
    public Siniestro actualizar(UUID id, Siniestro siniestro) {

        Siniestro entity = buscarPorId(id);

        if (entity == null) {
            throw new WebApplicationException("Siniestro no encontrado", 404);
        }

        entity.setId_asegurado(siniestro.getId_asegurado());
        entity.setIdentificacion_asegurado(siniestro.getIdentificacion_asegurado());
        entity.setNombre_asegurado(siniestro.getNombre_asegurado());

        entity.setId_vehiculo(siniestro.getId_vehiculo());
        entity.setPlaca(siniestro.getPlaca());

        /*entity.setId_usuario_perito(siniestro.getId_usuario_perito());
        entity.setIdentificacion_perito(siniestro.getIdentificacion_perito());
        entity.setNombre_perito(siniestro.getNombre_perito());*/

        entity.setUbicacion(siniestro.getUbicacion());
        entity.setDetalles(siniestro.getDetalles());
        entity.setPersonasHeridas(siniestro.getPersonasHeridas());
        entity.setNecesitaGrua(siniestro.getNecesitaGrua());
        //entity.setActivo(siniestro.getActivo());
        entity.setEstado(siniestro.getEstado());

        return entity;
    }

}
