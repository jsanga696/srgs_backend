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

        Peritaje peritaje = buscarPorId(peritajeReq.id);

        PeritajeResponseDTO resp = new PeritajeResponseDTO();
        Asegurado asegurado;
        Vehiculo vehiculo;
        Usuario perito;
        Siniestro siniestro;


        if (peritaje.asegurado != null && peritaje.asegurado.id != null) {
            asegurado = aseguradoService.buscarPorId(peritaje.asegurado.id);
            
            resp.nombres_asegurado = asegurado.nombres;
            resp.identificacion_asegurado = asegurado.identificacion;
        }

        if (peritaje.vehiculo != null && peritaje.vehiculo.id != null) {
            vehiculo = vehiculoService.buscarPorId(peritaje.vehiculo.id);

            resp.chasis_vehiculo = vehiculo.getChasis();
            resp.marca_vehiculo = vehiculo.getMarca();
            resp.modelo_vehiculo = vehiculo.getModelo();
            resp.anio_vehiculo = vehiculo.getAnio_fabricacion();
        }

        if (peritaje.perito != null && peritaje.perito.id != null) {
            perito = usuarioService.buscarPorId(peritaje.perito.id);

            resp.nombres_perito = perito.nombres;
            resp.identificacion_perito = perito.identificacion;
        }

        if (peritaje.siniestro != null && peritaje.siniestro.id != null) {
            siniestro = siniestroService.buscarPorId(peritaje.siniestro.id);
            
            resp.detalles_peritaje = siniestro.detalles;
        }

        resp.codigo = peritaje.codigo;
        resp.fecha = peritaje.fecha;
        resp.procede = peritaje.procede;
        resp.id = peritaje.id;

        return resp;
    }

}
