package com.videogamesstore.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videogamesstore.api.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Crea la consulta SQL automáticamente nombrando el método
    Optional<Usuario> findByEmail(String email);
}