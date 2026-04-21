package org.services;

import java.util.List;
import java.util.UUID;

import org.jsc.dtos.PeritajeResponseDTO;
import org.jsc.entities.Asegurado;
import org.jsc.entities.Peritaje;
import org.jsc.entities.Siniestro;
import org.jsc.entities.Usuario;
import org.jsc.entities.Vehiculo;
import org.jsc.repositories.PeritajeRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PeritajeService {
    
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

    public List<Peritaje> listar(int page, int size) {
        return repository.listar(page, size);
    }

    @Transactional
    public PeritajeResponseDTO crear(Peritaje peritajeReq) {

        repository.guardar(peritajeReq);

        Peritaje peritaje = buscarPorId(peritajeReq.getId());

        PeritajeResponseDTO resp = new PeritajeResponseDTO();
        Asegurado asegurado;
        Vehiculo vehiculo;
        Usuario perito;
        Siniestro siniestro;


        if (peritaje.getAsegurado() != null && peritaje.getAsegurado().getId() != null) {
            asegurado = aseguradoService.buscarPorId(peritaje.getAsegurado().getId());
            
            resp.nombres_asegurado = asegurado.getNombres();
            resp.identificacion_asegurado = asegurado.getIdentificacion();
        }

        if (peritaje.getVehiculo() != null && peritaje.getVehiculo().getId() != null) {
            vehiculo = vehiculoService.buscarPorId(peritaje.getVehiculo().getId());

            resp.chasis_vehiculo = vehiculo.getChasis();
            resp.marca_vehiculo = vehiculo.getMarca();
            resp.modelo_vehiculo = vehiculo.getModelo();
            resp.anio_vehiculo = vehiculo.getAnio_fabricacion();
        }

        if (peritaje.getPerito() != null && peritaje.getPerito().getId() != null) {
            perito = usuarioService.buscarPorId(peritaje.getPerito().getId());

            resp.nombres_perito = perito.getNombres();
            resp.identificacion_perito = perito.getIdentificacion();
        }

        if (peritaje.getSiniestro() != null && peritaje.getSiniestro().getId() != null) {
            siniestro = siniestroService.buscarPorId(peritaje.getSiniestro().getId());
            
            resp.detalles_peritaje = siniestro.getDetalles();
        }

        resp.codigo = peritaje.getCodigo();
        resp.fecha = peritaje.getFecha();
        resp.procede = peritaje.getProcede();
        resp.id = peritaje.getId();

        return resp;
    }

}
