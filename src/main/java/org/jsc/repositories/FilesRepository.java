package org.jsc.repositories;

import java.util.UUID;

import org.jsc.entities.Archivo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FilesRepository implements PanacheRepository<Archivo>{
    
    public Archivo buscarPorID(UUID id) {        
        return find("id", id).firstResult();
    }

}
