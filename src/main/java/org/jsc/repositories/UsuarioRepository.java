package org.jsc.repositories;

import java.util.List;

import org.jsc.entities.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario buscarPorId(Long id) {
        return find("id", id).firstResult();
    }

    public List<Usuario> listar(int page, int size) {
        return findAll().page(page, size).list();
    }

    public Usuario guardar(Usuario usuario) {
        persist(usuario);
        return usuario;
    }

    public List<Usuario> buscarActivos() {
        return list("activo", true);
    }

}
