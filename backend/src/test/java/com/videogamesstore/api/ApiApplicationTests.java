package com.videogamesstore.api;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.videogamesstore.api.dto.RegistroRequest;
import com.videogamesstore.api.entities.Videojuego;
import com.videogamesstore.api.repositories.VideojuegoRepository;
import com.videogamesstore.api.services.AuthService;
import com.videogamesstore.api.services.VideojuegoService;

@SpringBootTest
@Transactional
class ApiApplicationTests {

    @Autowired private AuthService authService;
    @Autowired private VideojuegoService videojuegoService;
    @Autowired private VideojuegoRepository videojuegoRepository;
    @Autowired private com.videogamesstore.api.services.PedidoService pedidoService;

    @Test
    void test01_RegistroUsuarioExitoso() {
        RegistroRequest req = new RegistroRequest();
        req.setNickname("testUser");
        req.setEmail("test1@ejemplo.com");
        req.setPassword("12345");
        assertDoesNotThrow(() -> authService.registrar(req));
    }

    @Test
    void test02_EvitarCorreoDuplicado() {
        RegistroRequest req = new RegistroRequest();
        req.setNickname("clon");
        req.setEmail("duplicado@ejemplo.com");
        req.setPassword("12345");
        authService.registrar(req);
        assertThrows(RuntimeException.class, () -> authService.registrar(req));
    }

    @Test
    void test03_LoginFallidoPasswordIncorrecta() {
        RegistroRequest req = new RegistroRequest();
        req.setNickname("hacker");
        req.setEmail("seguro@ejemplo.com");
        req.setPassword("claveCorrecta");
        authService.registrar(req);
        assertThrows(RuntimeException.class, () -> authService.iniciarSesion("seguro@ejemplo.com", "claveFALSA"));
    }

    @Test
    void test04_ObtenerCatalogo() {
        // Método dinámico enviando nulls
        List<Videojuego> catalogo = videojuegoService.obtenerFiltrados(null, null, null, null);
        assertNotNull(catalogo);
    }

    @Test
    void test05_RendimientoServicioVideojuegos() {
        // Método dinámico
        assertTimeout(Duration.ofMillis(500), () -> videojuegoService.obtenerFiltrados(null, null, null, null));
    }

    @Test
    void test06_RendimientoFiltradoPorCategoria() {
        // Método de filtrado completo del servicio
        assertTimeout(Duration.ofMillis(300), () -> {
            videojuegoService.obtenerFiltrados(null, "RPG", null, null);
        });
    }

    @Test
    void test07_FalloPorStockInsuficiente() {
        // Asumiendo que el usuario 'santiago_dev' y el juego ID 1 existen por el data.sql
        com.videogamesstore.api.dto.PedidoRequest req = new com.videogamesstore.api.dto.PedidoRequest();
        com.videogamesstore.api.dto.ItemCarritoDTO item = new com.videogamesstore.api.dto.ItemCarritoDTO();
        
        item.setIdVideojuego(1); // Juego existente
        item.setCantidad(9999); // Cantidad exagerada para forzar el error de stock
        req.setItems(List.of(item));

        // Debe lanzar una excepción debido al stock insuficiente y hacer Rollback
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.procesarCheckout("santiago@example.com", req);
        });
        
        // Verificamos que el mensaje sea el correcto
        assertNotNull(exception.getMessage());
        org.junit.jupiter.api.Assertions.assertTrue(exception.getMessage().contains("Stock insuficiente"));
    }
}