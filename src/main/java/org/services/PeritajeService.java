package org.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.jsc.dtos.PageResponse;
import org.jsc.dtos.PeritajeDTO;
import org.jsc.entities.Archivo;
import org.jsc.entities.Peritaje;
import org.jsc.entities.Siniestro;
import org.jsc.repositories.ArchivoRepository;
import org.jsc.repositories.PeritajeRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PeritajeService {
    
    @ConfigProperty(name = "app.upload.dir")
    String uploadDir;

    @Inject
    ObjectMapper mapper;

    @Inject
    ArchivoRepository archivoRepository;

    @Inject
    PeritajeRepository repository;

    @Inject
    AseguradoService aseguradoService;

    @Inject
    VehiculoService vehiculoService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    SiniestroService siniestroService;

    public Peritaje buscarPorId(UUID id) {

        Peritaje peritaje = repository.buscarPorId(id);

        if (peritaje == null) {
            throw new RuntimeException("Peritaje no encontrado");
        }

        return peritaje;
    }

    public PageResponse<Peritaje> listar(int page, int size, String codigo, String nombres) {
        return repository.listar(page, size, codigo, nombres);
    }

    @Transactional
    public Peritaje crear(Peritaje peritajeReq) {

        repository.guardar(peritajeReq);

        return peritajeReq;
    }

    @Transactional
    public Peritaje guardar(String dataJson, List<FileUpload> files) {

        try {
            PeritajeDTO dto = mapper.readValue(dataJson, PeritajeDTO.class);

            Path folder = Paths.get(uploadDir, dto.getCodigoSiniestro() + "P");

            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }

            Siniestro siniestro = siniestroService.buscarPorId(dto.getId_siniestro());

            if(siniestro == null){
                throw new WebApplicationException("Siniestro no encontrado", Response.Status.NOT_FOUND);
            }
            
            siniestro.setEstado("Peritado");

            Peritaje peritaje = new Peritaje();
            peritaje.setDetalles(dto.getDetalles());
            peritaje.setFecha(LocalDateTime.now());
            peritaje.setCodigo(dto.getCodigoSiniestro() + "P");
            peritaje.setId_asegurado(dto.getIdAsegurado());
            peritaje.setId_siniestro(dto.getId_siniestro());
            peritaje.setId_usuario_perito(dto.getIdUsuarioPerito());
            peritaje.setId_vehiculo(dto.getIdVehiculo());
            peritaje.setIdentificacion_asegurado(dto.getIdentificacion_asegurado());
            peritaje.setIdentificacion_perito(dto.getIdentificacion_perito());
            peritaje.setNombre_asegurado(dto.getNombre_asegurado());
            peritaje.setNombre_perito(dto.getNombre_perito());
            peritaje.setPlaca(dto.getPlacaVehiculo());
            peritaje.setProcede(dto.getProcede());
            peritaje.setRuta_archivos(folder.toString());

            repository.persist(peritaje);

            if (files != null) {

                for (FileUpload file : files) {                    

                    Files.copy(
                        file.uploadedFile(),
                        folder.resolve(folder.resolve(file.fileName())),
                        StandardCopyOption.REPLACE_EXISTING
                    );

                    Archivo archivo = new Archivo();
                    archivo.setNombre(file.fileName());
                    archivo.setPeritaje(peritaje);

                    archivoRepository.persist(archivo);
                }
            }
            
            return peritaje;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
