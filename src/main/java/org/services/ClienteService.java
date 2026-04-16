package org.services;

import java.util.List;

import org.jsc.entities.Cliente;
import org.jsc.repositories.ClienteRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClienteService {
    
    @Inject
    ClienteRepository repository;

    public Cliente buscarPorId(Long id) {

        Cliente cliente = repository.buscarPorId(id);

        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        return cliente;
    }

    public List<Cliente> listar(int page, int size) {
        return repository.listar(page, size);
    }

    public Cliente crear(Cliente usuario) {
        return repository.guardar(usuario);
    }

}
