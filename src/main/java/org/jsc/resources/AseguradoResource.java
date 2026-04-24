package org.jsc.resources;

import java.util.List;
import java.util.UUID;

import org.jsc.dtos.PageResponse;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Empresa;
import org.jsc.entities.Vehiculo;
import org.services.AseguradoService;
import org.services.EmpresaService;
import org.services.VehiculoService;

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

@Path("/asegurados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AseguradoResource {
    
    @Inject
    AseguradoService services;

    @GET
    public PageResponse<Asegurado> listar(@QueryParam("page") int page,
                        @QueryParam("size") int size,
                        @QueryParam("identificacion") String identificacion,
                    @QueryParam("nombres") String nombres) {
        return services.listar(page, size, identificacion, nombres);
    }

    @GET
    @Path("/{id}")
    public Asegurado obtener(@PathParam("id") UUID id) {
        return services.buscarPorId(id);
    }

    @GET
    @Path("/{identificacion}")
    public Asegurado obtenerPorIdentificacion(@PathParam("identificacion") String identificacion) {
        return services.buscarPorIdentificacion(identificacion);
    }

    @POST
    public Response crear(Asegurado asegurado) {

        Asegurado nuevo = services.crear(asegurado);

        return Response.status(Response.Status.CREATED)
                .entity(nuevo)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") UUID id, Asegurado data) {

        try{
            Asegurado nuevo = services.actualizar(id, data);

            return Response.status(Response.Status.CREATED)
                    .entity(nuevo)
                    .build();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
