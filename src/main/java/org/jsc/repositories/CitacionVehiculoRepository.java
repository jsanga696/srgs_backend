package org.jsc.repositories;

import java.util.Optional;

import org.jsc.entities.CitacionVehiculo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CitacionVehiculoRepository implements PanacheRepository<CitacionVehiculo> {

    public Optional<CitacionVehiculo> findByExterno(String idExterno) {
        return find("id", idExterno).firstResultOptional();
    }

}
