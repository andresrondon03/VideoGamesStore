package com.videogamesstore.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videogamesstore.api.entities.Videojuego;
import com.videogamesstore.api.repositories.VideojuegoRepository;

@Service
public class VideojuegoService {
    
    @Autowired
    private VideojuegoRepository repository;

    public List<Videojuego> obtenerTodos() {
        return repository.findAll();
    }

    public Videojuego crearVideojuego(Videojuego videojuego) {
        return repository.save(videojuego);
    }

    public Videojuego actualizar(Integer id, Videojuego detalles) {
        Videojuego vj = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));
        
        vj.setTitulo(detalles.getTitulo());
        vj.setDescripcion(detalles.getDescripcion());
        vj.setPrecioBase(detalles.getPrecioBase());
        vj.setStock(detalles.getStock());
        vj.setCategorias(detalles.getCategorias());
        vj.setPlataformas(detalles.getPlataformas());
        
        return repository.save(vj);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}