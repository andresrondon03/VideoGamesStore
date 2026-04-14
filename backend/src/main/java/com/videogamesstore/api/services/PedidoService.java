package com.videogamesstore.api.services;

import com.videogamesstore.api.dto.PedidoRequest;
import com.videogamesstore.api.entities.Pedido;
import com.videogamesstore.api.entities.Usuario;
import com.videogamesstore.api.entities.Videojuego;
import com.videogamesstore.api.repositories.PedidoRepository;
import com.videogamesstore.api.repositories.UsuarioRepository;
import com.videogamesstore.api.repositories.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private VideojuegoRepository videojuegoRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Transactional
    public Pedido procesarCheckout(PedidoRequest request) {
        // 1. Buscar al usuario
        Usuario usuario = usuarioRepository.findByEmail(request.getEmailUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        double total = 0.0;

        // 2. Procesar cada videojuego del carrito
        for (Integer idJuego : request.getIdVideojuegos()) {
            Videojuego vj = videojuegoRepository.findById(idJuego)
                    .orElseThrow(() -> new RuntimeException("Juego no encontrado con ID: " + idJuego));

            // Regla de Negocio: Validar Stock
            if (vj.getStock() <= 0) {
                throw new RuntimeException("Sin stock disponible para: " + vj.getTitulo());
            }

            // Reducir el inventario
            vj.setStock(vj.getStock() - 1);
            videojuegoRepository.save(vj);

            // Sumar al total
            total += vj.getPrecioBase();
        }

        // 3. Crear y guardar el registro del pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(total);
        pedido.setEstado("Completado"); // Simulación de pago exitoso

        return pedidoRepository.save(pedido);
    }
}