package com.videogamesstore.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videogamesstore.api.dto.PedidoRequest;
import com.videogamesstore.api.entities.Pedido;
import com.videogamesstore.api.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/checkout")
    public ResponseEntity<?> realizarCompra(@RequestBody PedidoRequest request) {
        try {
            Pedido nuevoPedido = pedidoService.procesarCheckout(request);
            return ResponseEntity.ok(nuevoPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}