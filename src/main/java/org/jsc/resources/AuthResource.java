package org.jsc.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jsc.dtos.LoginRequest;
import org.jsc.dtos.LoginResponse;
import org.jsc.entities.Usuario;
import org.services.AuthService;
import org.services.UsuarioService;

@Path("/auth")
public class AuthResource {

    @Inject
    AuthService service;

    @Inject
    private UsuarioService usuarioService;

    @POST
    @Path("/login")
    public LoginResponse login(LoginRequest request) {

        if (usuarioService.buscarPorCredenciales(request.getUsername(), request.getPassword()) == null) {
            throw new WebApplicationException("Credenciales inválidas", 401);
        }

        String token = service.login(request.getUsername(), request.getPassword());

        return new LoginResponse(token);

    }
}