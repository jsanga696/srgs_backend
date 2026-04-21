package org.jsc.resources;

import java.util.List;

import org.jsc.entities.Cliente;
import org.jsc.entities.Empresa;
import org.services.ClienteService;
import org.services.EmpresaService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {
    
    @Inject
    ClienteService services;

    @Inject
    EmpresaService empresaServices;

    @GET
    @Path("/{page}/{size}")
    public List<Cliente> listar(@PathParam("page") int page,
                          @PathParam("size") int size) {
        return services.listar(page, size);
    }

    @GET
    @Path("/{id}")
    public Cliente obtener(@PathParam("id") Long id) {
        return services.buscarPorId(id);
    }

    @POST
    @Transactional
    public Cliente crear(Cliente user) {

        if (user.getEmpresa() != null && user.getEmpresa().getId() != null) {
            Empresa emp = empresaServices.buscarPorId(user.getEmpresa().getId());
            user.setEmpresa(emp);
        }

        services.crear(user);
        return user;
    }
    
}
