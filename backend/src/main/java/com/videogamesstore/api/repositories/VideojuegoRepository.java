package com.videogamesstore.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videogamesstore.api.entities.Videojuego;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Integer> {
    
    List<Videojuego> findByTituloContainingIgnoreCase(String titulo);

    List<Videojuego> findByPrecioBaseLessThanEqual(Double precio);
    
    List<Videojuego> findByPlataformasNombreIgnoreCase(String nombrePlataforma);

    List<Videojuego> findByCategoriasNombreIgnoreCase(String nombreCategoria);
}