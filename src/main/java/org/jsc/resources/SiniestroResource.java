package org.jsc.resources;

import java.util.List;
import java.util.UUID;

import org.jsc.dtos.PageResponse;
import org.jsc.dtos.SiniestroDTO;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Empresa;
import org.jsc.entities.Siniestro;
import org.jsc.entities.Usuario;
import org.services.SiniestroService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/siniestros")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SiniestroResource {
    
    @Inject
    SiniestroService services;

    @GET
    public PageResponse<Siniestro> listar(@QueryParam("page") int page,
                        @QueryParam("size") int size,
                        @QueryParam("nombres") String nombres,
                        @QueryParam("codigo") String codigo,
                        @QueryParam("estado") String estado) {

        return services.listar(page, size, nombres, codigo, estado);
    }

    @GET
    @Path("/{id}")
    public Siniestro obtener(@PathParam("id") UUID id) {
        return services.buscarPorId(id);
    }

    @POST
    public Response crear(Siniestro siniestro) {

        if (siniestro == null) {
            throw new WebApplicationException("Asegurado requerido", 400);
        }

        Siniestro nuevo = services.crear(siniestro);

        return Response.status(Response.Status.CREATED)
                .entity(nuevo)
                .build();

    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") UUID id, Siniestro siniestro) {

        if (siniestro == null || id == null) {
            throw new WebApplicationException("Siniestro requerido", 400);
        }

        Siniestro actualizado = services.actualizar(id, siniestro);

        return Response.ok(actualizado).build();
    }
    
}
