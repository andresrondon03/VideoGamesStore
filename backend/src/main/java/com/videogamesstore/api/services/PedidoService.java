package com.videogamesstore.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.videogamesstore.api.dto.ItemCarritoDTO;
import com.videogamesstore.api.dto.PedidoRequest;
import com.videogamesstore.api.entities.DetallePedido;
import com.videogamesstore.api.entities.Pedido;
import com.videogamesstore.api.entities.Usuario;
import com.videogamesstore.api.entities.Videojuego;
import com.videogamesstore.api.repositories.PedidoRepository;
import com.videogamesstore.api.repositories.UsuarioRepository;
import com.videogamesstore.api.repositories.VideojuegoRepository;

@Service
public class PedidoService {

    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private VideojuegoRepository videojuegoRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Transactional
    public Pedido procesarCheckout(String emailUsuario, PedidoRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(request.getItems() == null || request.getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEstado("Completado");

        double total = 0.0;

        for (ItemCarritoDTO item : request.getItems()) {
            Videojuego vj = videojuegoRepository.findById(item.getIdVideojuego())
                    .orElseThrow(() -> new RuntimeException("Juego no encontrado: " + item.getIdVideojuego()));

            if (vj.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + vj.getTitulo() + " (Disponibles: " + vj.getStock() + ")");
            }

            // 1. Reducir stock
            vj.setStock(vj.getStock() - item.getCantidad());
            videojuegoRepository.save(vj);

            // 2. Calcular precio real con descuento
            int porcentajeDescuento = (vj.getDescuento() != null) ? vj.getDescuento() : 0;
           double precioFinal = vj.getPrecio() * (1 - (porcentajeDescuento / 100.0));

            // 3. Crear el detalle del pedido
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setVideojuego(vj);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(precioFinal); // Guardar el precio con descuento
            
            pedido.getDetalles().add(detalle);

            // 4. Calcular subtotal acumulado
            total += (precioFinal * item.getCantidad());
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerHistorialPorEmail(String email) {
        return pedidoRepository.findByUsuarioEmail(email);
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }
}