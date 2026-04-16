package org.jsc.repositories;

import java.util.List;
import java.util.UUID;

import org.jsc.entities.Siniestro;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SiniestroRepository implements PanacheRepository<Siniestro>{
    
    public Siniestro buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public List<Siniestro> listar(int page, int size) {
        return findAll().page(page, size).list();
    }

    public Siniestro guardar(Siniestro siniestro) {
        persist(siniestro);
        return siniestro;
    }

}
