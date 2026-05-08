package com.videogamesstore.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videogamesstore.api.entities.Plataforma;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Integer> {
}
