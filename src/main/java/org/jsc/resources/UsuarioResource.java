package org.jsc.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.jsc.entities.Usuario;
import org.services.EmpresaService;
import org.services.UsuarioService;
import org.jsc.entities.Empresa;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService services;

    @Inject
    EmpresaService empresaServices;

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

    @PUT
    @Path("/{id}")
    @Transactional
    public Usuario actualizar(@PathParam("id") Long id, Usuario data) {
        Usuario u = services.buscarPorId(id);
        if (u == null) throw new NotFoundException();

        u.username = data.username;
        u.password = data.password;
        u.rol = data.rol;

        if (data.empresa != null && data.empresa.id != null) {
            u.empresa = empresaServices.buscarPorId(data.empresa.id);
        }

        return u;
    }

    /*@DELETE
    @Path("/{id}")
    @Transactional
    public void eliminar(@PathParam("id") Long id) {
        Usuario u = services.buscarPorId(id);
        if (u != null) em.remove(u);
    }*/
}