package com.videogamesstore.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videogamesstore.api.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    // Si más adelante el usuario quiere ver su historial, este método lo hace automático
    java.util.List<Pedido> findByUsuarioEmail(String email);
}