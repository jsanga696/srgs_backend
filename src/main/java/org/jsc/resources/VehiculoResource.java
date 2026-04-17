package org.jsc.resources;

import java.util.List;
import java.util.UUID;

import org.jsc.entities.Empresa;
import org.jsc.entities.Usuario;
import org.jsc.entities.Vehiculo;
import org.services.AseguradoService;
import org.services.VehiculoService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


@Path("/vehiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehiculoResource {
    
    @Inject
    VehiculoService services;

    @Inject
    AseguradoService aseguradoService;
    
    @GET
    public List<Vehiculo> listar(@QueryParam("page") int page,
                        @QueryParam("size") int size,
                        @QueryParam("placa") String placa) {
        return services.listar(page, size, placa);
    }

    @GET
    @Path("/{id}")
    public Vehiculo obtener(@PathParam("id") UUID id) {
        return services.buscarPorId(id);
    }

    @POST
    public Vehiculo crear(Vehiculo vehiculo) {
        services.crear(vehiculo);
        return vehiculo;
    }
    
}
