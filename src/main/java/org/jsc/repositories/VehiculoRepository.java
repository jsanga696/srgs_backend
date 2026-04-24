package org.jsc.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jsc.dtos.PageResponse;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Vehiculo;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VehiculoRepository implements PanacheRepository<Vehiculo>{
    
    public Optional<Vehiculo> findByPlaca(String placa) {
        return find("placa", placa).firstResultOptional();
    }

    public Vehiculo buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public PageResponse<Vehiculo> listar(int page, int size, String placa) {
        
        PanacheQuery<Vehiculo> query;

        if(placa != null && !placa.isEmpty())
                query = find("lower(placa) ilike ?1 order by placa desc", "%" + placa.toLowerCase() + "%");
            else
                query = findAll(Sort.by("placa").descending());

        long total = findAll().count();

        List<Vehiculo> data = query
                .page(page, size)
                .list();

        return new PageResponse<>(data, total, page, size);
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        persist(vehiculo);
        return vehiculo;
    }
}
