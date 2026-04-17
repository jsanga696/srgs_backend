package org.services;

import java.util.List;

import org.jsc.dtos.VehiculoCitaciones;
import org.jsc.entities.CitacionVehiculo;
import org.jsc.repositories.CitacionVehiculoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CitacionVehiculoService {
    
    @Inject
    CitacionVehiculoRepository repository;

    public void guardarCitaciones(VehiculoCitaciones citaciones) {
        /*for (CitacionVehiculo c : citaciones) {
            repository.persist(c);
        }*/
    }
}
