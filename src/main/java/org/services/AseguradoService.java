package org.services;

import java.util.List;
import java.util.UUID;

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

    public List<Asegurado> listar(int page, int size) {
        return repository.listar(page, size);
    }

    /*public Asegurado crear(Asegurado asegurado) {
        return repository.guardar(asegurado);
    }*/

    @Transactional
    public Asegurado crear(Asegurado asegurado) {

        if (asegurado.empresa != null && asegurado.empresa.id != null) {
            Empresa emp = empresaServices.buscarPorId(asegurado.empresa.id);
            asegurado.empresa = emp;
        }

        if (asegurado.vehiculos != null) {
            for (Vehiculo item : asegurado.vehiculos) {
                item.asegurado = asegurado;
            }
        }else{
            System.out.println("Es NULL");
        }

        repository.guardar(asegurado);

        return asegurado;
    }

}
