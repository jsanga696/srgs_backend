package org.jsc.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jsc.entities.Vehiculo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VehiculoRepository implements PanacheRepository<Vehiculo>{
    
    public Optional<Vehiculo> findByPlaca(String placa) {
        return find("placa", placa).firstResultOptional();
    }

    public Vehiculo buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public List<Vehiculo> listar(int page, int size, String placa) {
        if (placa != null && !placa.isEmpty()) {
            return find("LOWER(placa) like ?1",
                    "%" + placa.toLowerCase() + "%")
                    .page(page, size)
                    .list();
        }

        return findAll()
                .page(page, size)
                .list();
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        persist(vehiculo);
        return vehiculo;
    }
}
