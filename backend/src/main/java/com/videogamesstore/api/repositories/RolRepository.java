package com.videogamesstore.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videogamesstore.api.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRol(String rol);
}