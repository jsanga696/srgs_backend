package org.jsc.repositories;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jsc.dtos.PageResponse;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Peritaje;
import org.jsc.entities.Siniestro;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PeritajeRepository implements PanacheRepository<Peritaje>{
    
    public Peritaje buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public PageResponse<Peritaje> listar(int page, int size, String codigo, String nombres) {

        StringBuilder queryStr = new StringBuilder("1=1");
        Map<String, Object> params = new HashMap<>();

        if (nombres != null && !nombres.isEmpty()) {
            queryStr.append(" and lower(nombre_asegurado) like :nombres");
            params.put("nombres", "%" + nombres.toLowerCase() + "%");
        }

        if (codigo != null && !codigo.isEmpty()) {
            queryStr.append(" and lower(codigo) like :identificacion");
            params.put("identificacion", "%" + codigo.toLowerCase() + "%");
        }

        PanacheQuery<Peritaje> query = find(queryStr.toString(), Sort.by("fecha").descending(), params);

        long total = findAll().count();

        List<Peritaje> data = query
            .page(page, size)
            .list();

        return new PageResponse<>(data, total, page, size);

        /*PanacheQuery<Peritaje> query;

        if(nombres != null && !nombres.isEmpty())
            query = find("lower(nombre_asegurado) ilike ?1 order by fecha desc", "%" + nombres.toLowerCase() + "%");
        else
            query = findAll(Sort.by("fecha").descending());
        
        long total = query.count();

        List<Peritaje> data = query
            .page(page, size)
            .list();

        return new PageResponse<>(data, total, page, size);*/
    }

    public Peritaje guardar(Peritaje peritaje) {

        LocalDate fecha = LocalDate.now();

        int anio = fecha.getYear();
        int mes = fecha.getMonthValue();
        int sec = 1;

        List tmp = find("codigo ilike ?1", (anio + mes + "-" + peritaje.getIdentificacion_asegurado()) + "-%").list();

        if(tmp != null){
            sec = tmp.size() + 1;
        }

        peritaje.setCodigo(anio + mes + "-" + peritaje.getIdentificacion_asegurado() + "-" + sec);

        persist(peritaje);
        return peritaje;
    }

}
