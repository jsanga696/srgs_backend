package org.jsc.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.jsc.entities.Usuario;
import org.services.EmpresaService;
import org.services.UsuarioService;
import org.jsc.dtos.PageResponse;
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
    public PageResponse<Usuario> listar(@QueryParam("page") int page,
                                @QueryParam("size") int size,
                                @QueryParam("nombres") String nombres) {
        return services.listar(page, size, nombres);
    }

    @GET
    @Path("/{id}")
    public Usuario obtener(@PathParam("id") Long id) {
        return services.buscarPorId(id);
    }

    @POST
    @Transactional
    public Usuario crear(Usuario user) {

        if (user.getEmpresa() != null && user.getEmpresa().getId() != null) {
            Empresa emp = empresaServices.buscarPorId(user.getEmpresa().getId());
            user.setEmpresa(emp);
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

        u.setUsername(data.getUsername());
        u.setPassword(data.getPassword());
        u.setRol(data.getRol());

        if (data.getEmpresa() != null && data.getEmpresa().getId() != null) {
            u.setEmpresa(empresaServices.buscarPorId(data.getEmpresa().getId()));
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