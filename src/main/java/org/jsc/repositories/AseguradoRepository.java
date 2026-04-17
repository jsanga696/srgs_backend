package org.jsc.repositories;

import java.util.List;
import java.util.UUID;

import org.jsc.entities.Asegurado;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AseguradoRepository implements PanacheRepository<Asegurado>{
    
    public Asegurado buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public Asegurado buscarPorIdentificacionAsegurado(String identificacion) {
        return find("identificacion", identificacion).firstResult();
    }

    public List<Asegurado> listar(int page, int size, String identificacion) {
        if (identificacion != null && !identificacion.isEmpty()) {
            return find("identificacion = ?1 order by fecha_creacion desc ",
                    identificacion)
                    .page(page, size)
                    .list();
        }

        return findAll()
                .page(page, size)
                .list();
    }

    public Asegurado guardar(Asegurado asegurado) {
        persist(asegurado);
        return asegurado;
    }

}
