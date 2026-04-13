package com.videogamesstore.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videogamesstore.api.entities.Videojuego;
import com.videogamesstore.api.services.VideojuegoService;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService service;

    @GetMapping
    public List<Videojuego> listarTodos() {
        return service.obtenerTodos();
    }

    @PostMapping
    public Videojuego guardar(@RequestBody Videojuego videojuego) {
        return service.crearVideojuego(videojuego);
    }
}