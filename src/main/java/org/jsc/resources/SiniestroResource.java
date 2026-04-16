package org.jsc.resources;

import java.util.List;
import java.util.UUID;

import org.jsc.entities.Asegurado;
import org.jsc.entities.Empresa;
import org.jsc.entities.Siniestro;
import org.jsc.entities.Usuario;
import org.services.SiniestroService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/siniestros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SiniestroResource {
    
    @Inject
    SiniestroService services;

    @GET
    @Path("/{page}/{size}")
    public List<Siniestro> listar(@PathParam("page") int page,
                          @PathParam("size") int size) {
        return services.listar(page, size);
    }

    @GET
    @Path("/{id}")
    public Siniestro obtener(@PathParam("id") UUID id) {
        return services.buscarPorId(id);
    }

    @POST
    @Transactional
    public Response crear(Siniestro siniestro) {

        if (siniestro == null) {
            throw new WebApplicationException("Asegurado requerido", 400);
        }

        Siniestro nuevo = services.crear(siniestro);

        return Response.status(Response.Status.CREATED)
                .entity(nuevo)
                .build();

    }
    
}
