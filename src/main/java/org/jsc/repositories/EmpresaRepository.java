package org.jsc.repositories;

import java.util.List;

import org.jsc.entities.Empresa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmpresaRepository implements PanacheRepository<Empresa>{
    
    public Empresa buscarPorId(Long id) {
        return find("id", id).firstResult();
    }

    public List<Empresa> listar(int page, int size) {
        return findAll().page(page, size).list();
    }

    public Empresa guardar(Empresa empresa) {
        persist(empresa);
        return empresa;
    }

}
