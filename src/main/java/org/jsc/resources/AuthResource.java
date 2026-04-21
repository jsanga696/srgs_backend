package org.jsc.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jsc.dtos.LoginRequest;
import org.jsc.entities.Usuario;

@Path("/auth")
public class AuthResource {

    @Inject
    EntityManager em;

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {

        Usuario user = em.createQuery(
            "SELECT u FROM Usuario u WHERE u.username = :username",
            Usuario.class)
            .setParameter("username", request.username)
            .getResultStream()
            .findFirst()
            .orElse(null);

        if (user == null || !user.getPassword().equals(request.password)) {
            return Response.status(401).build();
        }

        // Validación de empresa
        if (!"SUPERADMIN".equals(user.getRol())) {
            if (user.getEmpresa() == null || 
                !user.getEmpresa().getId().equals(request.empresaId)) {
                return Response.status(403).entity("Empresa inválida").build();
            }
        }

        return Response.ok("Login correcto").build();
    }
}