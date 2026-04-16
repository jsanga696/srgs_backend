package org.jsc.repositories;

import java.util.List;
import java.util.UUID;

import org.jsc.entities.Vehiculo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VehiculoRepository implements PanacheRepository<Vehiculo>{
    
    public Vehiculo buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public List<Vehiculo> listar(int page, int size) {
        return findAll().page(page, size).list();
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        persist(vehiculo);
        return vehiculo;
    }
}
