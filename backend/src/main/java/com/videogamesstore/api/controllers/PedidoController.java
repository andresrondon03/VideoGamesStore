package com.videogamesstore.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
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
            // Seguridad: Extraer el email del usuario logueado en base al token
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String emailLogueado = auth.getName();

            Pedido nuevoPedido = pedidoService.procesarCheckout(emailLogueado, request);
            return ResponseEntity.ok(nuevoPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/historial")
    public ResponseEntity<List<Pedido>> obtenerMiHistorial() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return ResponseEntity.ok(pedidoService.obtenerHistorialPorEmail(email));
    }

    @GetMapping("/admin/todos")
    public ResponseEntity<List<Pedido>> obtenerTodosParaAdmin() {
        return ResponseEntity.ok(pedidoService.obtenerTodosLosPedidos());
    }
}