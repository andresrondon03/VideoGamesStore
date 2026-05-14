package com.videogamesstore.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequest {
    // El email se extraerá del token JWT por seguridad.
    // Lista de ítems con sus respectivas cantidades
    private List<ItemCarritoDTO> items; 
}