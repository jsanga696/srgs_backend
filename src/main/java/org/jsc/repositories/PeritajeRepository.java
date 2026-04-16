package org.jsc.repositories;

import java.util.List;
import java.util.UUID;

import org.jsc.entities.Peritaje;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PeritajeRepository implements PanacheRepository<Peritaje>{
    
    public Peritaje buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public List<Peritaje> listar(int page, int size) {
        return findAll().page(page, size).list();
    }

    public Peritaje guardar(Peritaje peritaje) {
        persist(peritaje);
        return peritaje;
    }

}
