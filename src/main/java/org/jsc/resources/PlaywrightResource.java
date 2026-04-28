package org.jsc.resources;

import org.jsc.dtos.VehiculoCitaciones;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/playwright")
@RolesAllowed("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaywrightResource {

    @Inject
    org.services.playwright.AtmScraperService service;

    @GET
    @Path("/atm/{placa}")
    public VehiculoCitaciones consultarPlaca(@PathParam("placa") String placa) {
        return service.consultar(placa);
    }
}