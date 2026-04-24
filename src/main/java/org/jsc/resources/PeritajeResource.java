package org.jsc.resources;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.jsc.dtos.PageResponse;
import org.jsc.dtos.PeritajeDTO;
import org.jsc.dtos.PeritajeResponseDTO;
import org.jsc.entities.Empresa;
import org.jsc.entities.Peritaje;
import org.jsc.entities.Siniestro;
import org.jsc.entities.Usuario;
import org.services.PeritajeService;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
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
    public PageResponse<Peritaje> listar(@QueryParam("page") int page,
                        @QueryParam("size") int size,
                        @QueryParam("nombres") String nombres) {
        return services.listar(page, size, nombres);
    }

    @GET
    @Path("/{id}")
    public Peritaje obtener(@PathParam("id") UUID id) {
        return services.buscarPorId(id);
    }

    @POST
    public Peritaje crear(Peritaje peritaje) {

        if (peritaje == null) {
            throw new WebApplicationException("Asegurado requerido", 400);
        }

        return services.crear(peritaje);
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/multipart")
    public Response crear(
        @RestForm("data") String dataJson,
        @RestForm("files") List<FileUpload> files
    ) {

        Peritaje peritaje = services.guardar(dataJson, files);

        return Response.status(Response.Status.CREATED)
            .entity(peritaje)
            .build();
    }
}
