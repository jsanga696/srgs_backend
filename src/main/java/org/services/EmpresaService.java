package org.services;

import java.util.List;

import org.jsc.dtos.PageResponse;
import org.jsc.entities.Empresa;
import org.jsc.repositories.EmpresaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmpresaService {
 
    @Inject
    EmpresaRepository repository;

    public PageResponse<Empresa> buscarEmpresa(int page, int size, String identificacion, String nombre, String razonSocial) {

        return repository.buscarEmpresa(page, size, identificacion, nombre, razonSocial);
    }

    public List<Empresa> listar(int page, int size) {
        return repository.listar(page, size);
    }

    public Empresa crear(Empresa empresa) {
        return repository.guardar(empresa);
    }

}
