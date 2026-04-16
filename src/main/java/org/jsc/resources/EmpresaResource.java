package org.jsc.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.jsc.entities.Empresa;

import java.util.List;

@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaResource {

    @Inject
    EntityManager em;

    @GET
    public List<Empresa> listar() {
        return em.createQuery("from Empresa", Empresa.class).getResultList();
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

        e.nombre = data.nombre;
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