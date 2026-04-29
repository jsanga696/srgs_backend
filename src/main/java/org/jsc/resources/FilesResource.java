package org.jsc.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import org.jsc.entities.Archivo;
import org.services.FilesService;
import org.services.SiniestroService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/files")
public class FilesResource {
    
    @Inject
    FilesService services;

    @GET
    @Path("/{id}")
    public Response descargar(@PathParam("id") UUID id) throws IOException {

        Archivo archivo = services.buscarPorID(id);
        String mimeType;

        if (archivo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        File file = new File(archivo.getPeritaje().getRuta_archivos() + "\\" +archivo.getNombre());

        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        mimeType = Files.probeContentType(file.toPath());

        return Response.ok(file, mimeType)
                .header("Content-Disposition", "inline; filename=\"" + archivo.getNombre() + "\"")
                .build();
    }
}
