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
        List<Videojuego> catalogo = videojuegoService.obtenerTodos();
        assertNotNull(catalogo);
    }

    @Test
    void test05_RendimientoServicioVideojuegos() {
        assertTimeout(Duration.ofMillis(500), () -> videojuegoService.obtenerTodos());
    }

    @Test
    void test06_RendimientoFiltradoPorCategoria() {
        // Prueba de rendimiento específica para el filtrado del RF-06
        assertTimeout(Duration.ofMillis(300), () -> {
            videojuegoRepository.findByCategoriasNombreIgnoreCase("RPG");
        });
    }
}