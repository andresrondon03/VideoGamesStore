package com.videogamesstore.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videogamesstore.api.entities.Videojuego;
import com.videogamesstore.api.repositories.VideojuegoRepository;

@Service
public class VideojuegoService {
    
    @Autowired
    private VideojuegoRepository repository;

    public List<Videojuego> obtenerFiltrados(String titulo, String categoria, String plataforma, Double precioMax) {
        List<Videojuego> lista = repository.findAll();

        if (titulo != null && !titulo.isBlank()) {
            lista = lista.stream()
                .filter(v -> v.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
        }
        if (categoria != null && !categoria.isBlank()) {
            lista = lista.stream()
                .filter(v -> v.getCategorias().stream().anyMatch(c -> c.getNombre().equalsIgnoreCase(categoria)))
                .collect(Collectors.toList());
        }
        if (plataforma != null && !plataforma.isBlank()) {
            lista = lista.stream()
                .filter(v -> v.getPlataformas().stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(plataforma)))
                .collect(Collectors.toList());
        }
        if (precioMax != null) {
            lista = lista.stream()
                .filter(v -> {
                    // Filtrar teniendo en cuenta el descuento aplicado
                    int desc = (v.getDescuento() != null) ? v.getDescuento() : 0;
                    double precioFinal = v.getPrecio() * (1 - (desc / 100.0));
                    return precioFinal <= precioMax;
                })
                .collect(Collectors.toList());
        }

        return lista;
    }

    public Videojuego crearVideojuego(Videojuego videojuego) {
        return repository.save(videojuego);
    }

    public Videojuego actualizar(Integer id, Videojuego detalles) {
        Videojuego vj = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));
        
        vj.setTitulo(detalles.getTitulo());
        vj.setDescripcion(detalles.getDescripcion());
        vj.setPrecio(detalles.getPrecio());
        vj.setStock(detalles.getStock());
        vj.setCategorias(detalles.getCategorias());
        vj.setPlataformas(detalles.getPlataformas());
        
        // Agregar los nuevos campos al método de actualización
        vj.setDescuento(detalles.getDescuento());
        vj.setImagenUrl(detalles.getImagenUrl());
        
        return repository.save(vj);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}