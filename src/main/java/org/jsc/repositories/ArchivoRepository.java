package org.jsc.repositories;

import org.jsc.entities.Archivo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArchivoRepository implements PanacheRepository<Archivo> {
    
    public Archivo guardar(Archivo archivo) {

        persist(archivo);
        return archivo;
    }

}
