package org.jsc.resources;

import org.jsc.entities.Empresa;
import org.jsc.entities.Usuario;
import org.services.VehiculoService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/vehiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehiculoResource {
    
    @Inject
    VehiculoService services;

    /* 
    @GET
    @Path("/{page}/{size}")
    public List<Usuario> listar(@PathParam("page") int page,
                          @PathParam("size") int size) {
        return services.listar(page, size);
    }

    @GET
    @Path("/{id}")
    public Usuario obtener(@PathParam("id") Long id) {
        return services.buscarPorId(id);
    }

    @POST
    @Transactional
    public Usuario crear(Usuario user) {

        if (user.empresa != null && user.empresa.id != null) {
            Empresa emp = empresaServices.buscarPorId(user.empresa.id);
            user.empresa = emp;
        }

        services.crear(user);
        return user;
    }
    */
}
