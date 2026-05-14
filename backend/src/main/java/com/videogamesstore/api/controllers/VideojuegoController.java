package com.videogamesstore.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.videogamesstore.api.entities.Videojuego;
import com.videogamesstore.api.services.VideojuegoService;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService service;

    // Filtros dinámicos
    @GetMapping
    public List<Videojuego> listarCatálogo(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String plataforma,
            @RequestParam(required = false) Double precioMax) {
        
        return service.obtenerFiltrados(titulo, categoria, plataforma, precioMax);
    }

    @PostMapping
    public Videojuego guardar(@RequestBody Videojuego videojuego) {
        return service.crearVideojuego(videojuego);
    }

    @PutMapping("/{id}")
    public Videojuego actualizar(@PathVariable Integer id, @RequestBody Videojuego videojuego) {
        return service.actualizar(id, videojuego);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}