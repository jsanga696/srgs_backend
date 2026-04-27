package org.jsc.repositories;

import java.util.List;

import org.jsc.dtos.PageResponse;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario buscarPorId(Long id) {
        return find("id", id).firstResult();
    }

    public Usuario buscarPorCredenciales(String username, String password) {
        return find("username = ?1 and password = ?2", username, password).firstResult();
    }

    public PageResponse<Usuario> listar(int page, int size, String nombres) {
        PanacheQuery<Usuario> query;

        if (nombres != null && !nombres.isEmpty()) {
            query = find("lower(nombres) ilike ?1 order by username desc", "%" + nombres.toLowerCase() + "%");
        } else {
            query = findAll(Sort.by("username").descending());
        }

        long total = query.count();

        List<Usuario> data = query
            .page(page, size)
            .list();

        return new PageResponse<>(data, total, page, size);
    }

    public Usuario guardar(Usuario usuario) {
        persist(usuario);
        return usuario;
    }

    public List<Usuario> buscarActivos() {
        return list("activo", true);
    }

}
