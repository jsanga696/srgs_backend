package org.jsc.resources;

import java.util.List;
import java.util.UUID;

import org.jsc.dtos.PeritajeResponseDTO;
import org.jsc.entities.Empresa;
import org.jsc.entities.Peritaje;
import org.jsc.entities.Siniestro;
import org.jsc.entities.Usuario;
import org.services.PeritajeService;

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

@Path("/peritajes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeritajeResource {
    
    @Inject
    PeritajeService services;

    @GET
    @Path("/{page}/{size}")
    public List<Peritaje> listar(@PathParam("page") int page,
                          @PathParam("size") int size) {
        return services.listar(page, size);
    }

    @GET
    @Path("/{id}")
    public Peritaje obtener(@PathParam("id") UUID id) {
        return services.buscarPorId(id);
    }

    @POST
    public Response crear(Peritaje peritaje) {

        if (peritaje == null) {
            throw new WebApplicationException("Asegurado requerido", 400);
        }

        PeritajeResponseDTO nuevo = services.crear(peritaje);

        return Response.status(Response.Status.CREATED)
                .entity(nuevo)
                .build();

    }
    
}
