package org.services;

import java.util.List;
import java.util.UUID;

import org.jsc.entities.Siniestro;
import org.jsc.repositories.SiniestroRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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

    public List<Siniestro> listar(int page, int size) {
        return repository.listar(page, size);
    }

    public Siniestro crear(Siniestro siniestro) {
        return repository.guardar(siniestro);
    }

}
