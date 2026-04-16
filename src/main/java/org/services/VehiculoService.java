package org.services;

import java.util.List;
import java.util.UUID;

import org.jsc.entities.Vehiculo;
import org.jsc.repositories.VehiculoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VehiculoService {

    @Inject
    VehiculoRepository repository;
    
    public Vehiculo buscarPorId(UUID id) {

        Vehiculo vehiculo = repository.buscarPorId(id);

        if (vehiculo == null) {
            throw new RuntimeException("Vehiculo no encontrado");
        }

        return vehiculo;
    }

    public List<Vehiculo> listar(int page, int size) {
        return repository.listar(page, size);
    }

    public Vehiculo crear(Vehiculo vehiculo) {
        return repository.guardar(vehiculo);
    }

}
