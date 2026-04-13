package com.videogamesstore.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videogamesstore.api.entities.Videojuego;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
    // Filtro por plataforma
    List<Videojuego> findByPlataformaIgnoreCase(String plataforma);
    
    // Filtro por precio máximo
    List<Videojuego> findByPrecioBaseLessThanEqual(Double precio);
    
    // Filtro por nombre (Buscador RF-05)
    List<Videojuego> findByTituloContainingIgnoreCase(String titulo);
}