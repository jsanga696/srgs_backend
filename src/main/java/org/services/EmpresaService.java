package org.services;

import java.util.List;

import org.jsc.entities.Empresa;
import org.jsc.repositories.EmpresaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmpresaService {
 
    @Inject
    EmpresaRepository repository;

    public Empresa buscarPorId(Long id) {

        Empresa empresa = repository.buscarPorId(id);

        if (empresa == null) {
            throw new RuntimeException("Empresa no encontrado");
        }

        return empresa;
    }

    public List<Empresa> listar(int page, int size) {
        return repository.listar(page, size);
    }

    public Empresa crear(Empresa empresa) {
        return repository.guardar(empresa);
    }

}
