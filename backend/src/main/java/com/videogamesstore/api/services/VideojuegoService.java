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
        // Por ahora lo guardamos directo sin validaciones de plataforma
        return repository.save(videojuego);
    }
}