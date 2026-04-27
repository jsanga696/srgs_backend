package org.jsc.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.jsc.dtos.PageResponse;
import org.jsc.entities.Empresa;
import org.services.EmpresaService;

@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaResource {

    @Inject
    EntityManager em;

    @Inject
    EmpresaService service;

    @GET
    public PageResponse<Empresa> listar(@QueryParam("page") int page,
                        @QueryParam("size") int size,
                        @QueryParam("identificacion") String identificacion,
                        @QueryParam("nombres") String nombres,
                        @QueryParam("razon_social") String razon_social) {
        return service.buscarEmpresa(page, size, identificacion, nombres, razon_social);
    }

    @GET
    @Path("/{id}")
    public Empresa obtener(@PathParam("id") Long id) {
        return em.find(Empresa.class, id);
    }

    @POST
    @Transactional
    public Empresa crear(Empresa empresa) {
        em.persist(empresa);
        return empresa;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Empresa actualizar(@PathParam("id") Long id, Empresa data) {
        Empresa e = em.find(Empresa.class, id);
        if (e == null) throw new NotFoundException();

        e.setNombre(data.getNombre());
        e.setRazon_social(data.getRazon_social());
        e.setCelular(data.getCelular());
        e.setCiudad(data.getCiudad());
        e.setDireccion(data.getDireccion());
        e.setEmail(data.getEmail());
        e.setPais(data.getPais());
        e.setProvincia(data.getProvincia());
        e.setTelefono(data.getTelefono());
        e.setRuc(data.getRuc());
        e.setActivo(data.getActivo());

        return e;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        Empresa e = em.find(Empresa.class, id);
        if (e != null) em.remove(e);
    }
}