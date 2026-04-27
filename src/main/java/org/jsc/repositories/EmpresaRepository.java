package org.jsc.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsc.dtos.PageResponse;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Empresa;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmpresaRepository implements PanacheRepository<Empresa>{
    
    public PageResponse<Empresa> buscarEmpresa(int page, int size, String identificacion, String nombre, String razonSocial) {
        StringBuilder queryStr = new StringBuilder("1=1");
        Map<String, Object> params = new HashMap<>();
        //long total;

        if (identificacion != null && !identificacion.isEmpty()) {
            queryStr.append(" and ruc like :identificacion");
            params.put("identificacion", "%" + identificacion + "%");
        }

        if (nombre != null && !nombre.isEmpty()) {
            queryStr.append(" and lower(nombre) like :nombre");
            params.put("nombre", "%" + nombre.toLowerCase() + "%");
        }

        if (razonSocial != null && !razonSocial.isEmpty()) {
            queryStr.append(" and lower(razon_social) like :razonSocial");
            params.put("razonSocial", "%" + razonSocial.toLowerCase() + "%");
        }

        PanacheQuery<Empresa> query = find(queryStr.toString(), Sort.by("fecha_creacion").descending(), params);

        long total = query.count(); 

        List<Empresa> data = query
            .page(page, size)
            .list();

        return new PageResponse<>(data, total, page, size);
    }

    public List<Empresa> listar(int page, int size) {
        return findAll().page(page, size).list();
    }

    public Empresa guardar(Empresa empresa) {
        persist(empresa);
        return empresa;
    }

}
