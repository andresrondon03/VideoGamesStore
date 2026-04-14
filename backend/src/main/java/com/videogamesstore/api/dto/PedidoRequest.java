package com.videogamesstore.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequest {
    // Recibimos el email temporalmente para identificar al usuario
    private String emailUsuario; 
    // Lista de IDs de los videojuegos que están en el carrito
    private List<Integer> idVideojuegos; 
}