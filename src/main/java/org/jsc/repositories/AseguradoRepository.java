package org.jsc.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.jsc.dtos.PageResponse;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Vehiculo;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class AseguradoRepository implements PanacheRepository<Asegurado>{
    
    @PersistenceContext
    EntityManager em;

    public Asegurado buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public Asegurado buscarPorIdentificacionAsegurado(String identificacion) {
        return find("identificacion", identificacion).firstResult();
    }

    public PageResponse<Asegurado> listar(int page, int size, String identificacion) {
        PanacheQuery<Asegurado> query;

        if (identificacion != null && !identificacion.isEmpty()) {
            query = find("identificacion = ?1 order by fecha_creacion desc", identificacion);

        } else {
            query = findAll(Sort.by("fecha_creacion").descending());
        }

        long total = query.count();

        List<Asegurado> data = query
            .page(page, size)
            .list();

        return new PageResponse<>(data, total, page, size);

    }
    
    public Asegurado actualizar(UUID id, Asegurado asegurado) {

        if (asegurado == null || id == null) {
            throw new WebApplicationException("Asegurado requerido", 400);
        }

        Asegurado entity = buscarPorId(id);

        entity.setIdentificacion(asegurado.getIdentificacion());
        entity.setNombres(asegurado.getNombres());
        entity.setEmpresa(asegurado.getEmpresa());
        entity.setActivo(asegurado.getActivo());
        entity.setCelular(asegurado.getCelular());
        entity.setTelefono(asegurado.getTelefono());
        entity.setDireccion(asegurado.getDireccion());
        entity.setEmail(asegurado.getEmail());
        entity.setPais(asegurado.getPais());
        entity.setProvincia(asegurado.getProvincia());
        entity.setCiudad(asegurado.getCiudad());

        entity.getVehiculos().size();
        entity.getVehiculos().clear();
        em.flush();

        for (Vehiculo v : asegurado.getVehiculos()) {

            Vehiculo nuevo = new Vehiculo();

            nuevo.setPlaca(v.getPlaca());
            nuevo.setMarca(v.getMarca());
            nuevo.setModelo(v.getModelo());
            nuevo.setColor(v.getColor());
            nuevo.setAnio_fabricacion(v.getAnio_fabricacion());
            nuevo.setFechaMatricula(v.getFechaMatricula());
            nuevo.setFechaCaducidad(v.getFechaCaducidad());

            nuevo.setAsegurado(entity);

            entity.getVehiculos().add(nuevo);
        }

        return entity;
    }

    public Asegurado guardar(Asegurado asegurado) {

        if (asegurado == null) {
            throw new WebApplicationException("Asegurado requerido", 400);
        }
        
        asegurado.setFecha_creacion(LocalDateTime.now());

        if (asegurado.getVehiculos() != null) {
            for (Vehiculo v : asegurado.getVehiculos()) {
                v.setAsegurado(asegurado); 
            }
        }

        persist(asegurado);
        return asegurado;
    }

}
