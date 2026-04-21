package org.services;

import java.util.List;
import java.util.UUID;

import org.jsc.dtos.PageResponse;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Empresa;
import org.jsc.entities.Vehiculo;
import org.jsc.repositories.AseguradoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AseguradoService {
    
    @Inject
    AseguradoRepository repository;

    @Inject
    EmpresaService empresaServices;

    @Inject
    VehiculoService vehiculoServices;

    public Asegurado buscarPorId(UUID id) {

        Asegurado asegurado = repository.buscarPorId(id);

        if (asegurado == null) {
            throw new RuntimeException("Asegurado no encontrado");
        }

        return asegurado;
    }

    public Asegurado buscarPorIdentificacion(String identificacion) {

        Asegurado asegurado = repository.buscarPorIdentificacionAsegurado(identificacion);

        if (asegurado == null) {
            throw new RuntimeException("Asegurado no encontrado");
        }

        return asegurado;
    }

    public PageResponse<Asegurado> listar(int page, int size, String identificacion) {
        return repository.listar(page, size, identificacion);
    }

    @Transactional
    public Asegurado crear(Asegurado asegurado) {
        return repository.guardar(asegurado);
    }

    @Transactional
    public Asegurado actualizar(UUID id, Asegurado asegurado) {

        Asegurado ret = repository.actualizar(id, asegurado);

        return ret;
    }

}
