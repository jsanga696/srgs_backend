package org.jsc.repositories;

import java.util.List;
import java.util.UUID;

import org.jsc.entities.Asegurado;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AseguradoRepository implements PanacheRepository<Asegurado>{
    
    public Asegurado buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public List<Asegurado> listar(int page, int size) {
        return findAll().page(page, size).list();
    }

    public Asegurado guardar(Asegurado asegurado) {
        persist(asegurado);
        return asegurado;
    }

}
