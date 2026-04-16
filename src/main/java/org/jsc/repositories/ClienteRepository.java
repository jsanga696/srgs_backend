package org.jsc.repositories;

import java.util.List;

import org.jsc.entities.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente>{
    
    public Cliente buscarPorId(Long id) {
        return find("id", id).firstResult();
    }

    public List<Cliente> listar(int page, int size) {
        return findAll().page(page, size).list();
    }

    public Cliente guardar(Cliente cliente) {
        persist(cliente);
        return cliente;
    }

}
