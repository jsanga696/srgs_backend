package org.services;

import java.util.UUID;

import org.jsc.entities.Archivo;
import org.jsc.entities.Peritaje;
import org.jsc.repositories.FilesRepository;
import org.jsc.repositories.SiniestroRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FilesService {
    
    @Inject
    FilesRepository repository;

    public Archivo buscarPorID(UUID id) {

        Archivo peritaje = repository.buscarPorID(id);

        if (peritaje == null) {
            throw new RuntimeException("Archivo no encontrado");
        }

        return peritaje;
    }
    
}
