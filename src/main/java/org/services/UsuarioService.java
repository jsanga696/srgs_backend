package org.services;

import java.util.List;

import org.jsc.entities.Usuario;
import org.jsc.repositories.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository repository;

    public Usuario buscarPorId(Long id) {

        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return usuario;
    }

    public List<Usuario> listar(int page, int size) {
        return repository.listar(page, size);
    }

    public Usuario crear(Usuario usuario) {
        //usuario.activo = true;
        return repository.guardar(usuario);
    }
    
}
