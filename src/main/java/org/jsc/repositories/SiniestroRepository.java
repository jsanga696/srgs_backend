package org.jsc.repositories;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jsc.dtos.PageResponse;
import org.jsc.dtos.SiniestroDTO;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Siniestro;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SiniestroRepository implements PanacheRepository<Siniestro>{
    
    public Siniestro buscarPorId(UUID id) {
        return find("id", id).firstResult();
    }

    public PageResponse<Siniestro> listar(int page, int size, String nombres, String codigo, String estado) {

        StringBuilder queryStr = new StringBuilder("1=1");
        Map<String, Object> params = new HashMap<>();

        if (nombres != null && !nombres.isEmpty()) {
            queryStr.append(" and lower(nombre_asegurado) like :nombres");
            params.put("nombres", "%" + nombres.toLowerCase() + "%");
        }

        if (codigo != null && !codigo.isEmpty()) {
            queryStr.append(" and lower(codigo) like :codigo");
            params.put("codigo", "%" + codigo.toLowerCase() + "%");
        }

        if (estado != null && !estado.isEmpty()) {
            queryStr.append(" and lower(estado) = :estado");
            params.put("estado", estado.toLowerCase());
        }

        PanacheQuery<Siniestro> query = find(queryStr.toString(), Sort.by("fecha").descending(), params);

        long total = findAll().count();

        List<Siniestro> data = query
            .page(page, size)
            .list();

        return new PageResponse<>(data, total, page, size);
    }

    public Siniestro guardar(Siniestro siniestro) {

        LocalDate fecha = LocalDate.now();

        int anio = fecha.getYear();
        int mes = fecha.getMonthValue();
        int sec = 1;

        List tmp = find("codigo ilike ?1", (anio + mes + "-" + siniestro.getIdentificacion_asegurado()) + "-%").list();

        if(tmp != null){
            sec = tmp.size() + 1;
        }

        siniestro.setEstado("Iniciado");
        siniestro.setCodigo(anio + mes + "-" + siniestro.getIdentificacion_asegurado() + "-" + sec);

        persist(siniestro);
        return siniestro;
    }

}
