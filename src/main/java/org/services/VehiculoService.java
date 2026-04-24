package org.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jsc.dtos.Citacion;
import org.jsc.dtos.PageResponse;
import org.jsc.dtos.VehiculoCitaciones;
import org.jsc.entities.Asegurado;
import org.jsc.entities.CitacionVehiculo;
import org.jsc.entities.Vehiculo;
import org.jsc.repositories.CitacionVehiculoRepository;
import org.jsc.repositories.VehiculoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VehiculoService {

    @Inject
    VehiculoRepository repository;

    @Inject
    CitacionVehiculoRepository citacionRepo;
    
    @Transactional
    public void guardarVehiculoConCitaciones(VehiculoCitaciones dto) {

        Vehiculo vehiculo = repository.findByPlaca(dto.getPlaca()).orElse(null);
        Vehiculo vehiculoNew;

        if (vehiculo != null) {
            vehiculo.setMarca(dto.getMarca());
            vehiculo.setColor(dto.getColor());
            vehiculo.setModelo(dto.getModelo());
            vehiculo.setAnio_fabricacion(dto.getAnio_fabricacion());
            vehiculo.setFechaCaducidad(dto.getFechaCaducidad());
            vehiculo.setFechaMatricula(dto.getFechaMatricula());
        } else {
            vehiculoNew = new Vehiculo();
            vehiculoNew.setPlaca(dto.getPlaca());
            vehiculoNew.setMarca(dto.getMarca());
            vehiculoNew.setColor(dto.getColor());
            vehiculoNew.setModelo(dto.getModelo());
            vehiculoNew.setAnio_fabricacion(dto.getAnio_fabricacion());
            vehiculoNew.setFechaCaducidad(dto.getFechaCaducidad());
            vehiculoNew.setFechaMatricula(dto.getFechaMatricula());

            repository.persist(vehiculoNew);
        }

        for (Citacion c : dto.getCitaciones()) {

            citacionRepo.findByExterno(c.getId())
                    .ifPresentOrElse(
                            existing -> {
                                // ya existe → no hacer nada
                            },
                            () -> {
                                CitacionVehiculo entity = new CitacionVehiculo();

                                entity.setId(c.getId());
                                entity.setDocumento(c.getDocumento());
                                entity.setDescripcion(c.getDescripcion());
                                entity.setFechaEmision(c.getFechaEmision() + "");
                                entity.setFechaVencimiento(c.getFechaVencimiento() + "");
                                entity.setValor(c.getValor().doubleValue());
                                entity.setEntidad(c.getEntidad());
                                entity.setTipo(c.getTipo());

                                entity.setVehiculo(vehiculo); 

                                citacionRepo.persist(entity);
                            }
                    );
        }
    }

    public Vehiculo buscarPorId(UUID id) {

        Vehiculo vehiculo = repository.buscarPorId(id);

        if (vehiculo == null) {
            throw new RuntimeException("Vehiculo no encontrado");
        }

        return vehiculo;
    }

    public Vehiculo buscarPorPlaca(String placa) {

        return repository.findByPlaca(placa).orElse(null);
        
    }

    public PageResponse<Vehiculo> listar(int page, int size, String placa) {
        return repository.listar(page, size, placa);
    }

    @Transactional
    public Vehiculo crear(Vehiculo vehiculo) {
        return repository.guardar(vehiculo);
    }

}
