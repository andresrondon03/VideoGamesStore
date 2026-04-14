package com.videogamesstore.api;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.videogamesstore.api.dto.RegistroRequest;
import com.videogamesstore.api.entities.Videojuego;
import com.videogamesstore.api.services.AuthService;
import com.videogamesstore.api.services.VideojuegoService;

@SpringBootTest
@Transactional // Revierte los cambios en la base de datos después de cada prueba
class ApiApplicationTests {

    @Autowired
    private AuthService authService;

    @Autowired
    private VideojuegoService videojuegoService;

    // 1. PRUEBA DE INTEGRACIÓN: Registro exitoso cruzando Servicio y Base de Datos
    @Test
    void test01_RegistroUsuarioExitoso() {
        RegistroRequest req = new RegistroRequest();
        req.setNickname("testUser");
        req.setEmail("test1@ejemplo.com");
        req.setPassword("12345");

        // Verifica que el método se ejecute sin lanzar errores
        assertDoesNotThrow(() -> authService.registrar(req));
    }

    // 2. PRUEBA UNITARIA / REGLA DE NEGOCIO: Evitar correos duplicados
    @Test
    void test02_EvitarCorreoDuplicado() {
        RegistroRequest req = new RegistroRequest();
        req.setNickname("clon");
        req.setEmail("duplicado@ejemplo.com");
        req.setPassword("12345");

        authService.registrar(req); // Primer registro exitoso

        // El segundo registro debe hacer "explotar" el sistema con una RuntimeException
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            authService.registrar(req);
        });
        
        // Verificamos que el mensaje de error sea exactamente el esperado
        assertEquals("El correo ya está en uso", excepcion.getMessage());
    }

    // 3. PRUEBA DE SEGURIDAD: Control de acceso con clave incorrecta
    @Test
    void test03_LoginFallidoPasswordIncorrecta() {
        RegistroRequest req = new RegistroRequest();
        req.setNickname("hacker");
        req.setEmail("seguro@ejemplo.com");
        req.setPassword("claveCorrecta");
        authService.registrar(req); // Creamos el usuario válido

        // Intentamos iniciar sesión con una clave falsa
        assertThrows(RuntimeException.class, () -> {
            authService.iniciarSesion("seguro@ejemplo.com", "claveINCORRECTA");
        });
    }

    // 4. PRUEBA FUNCIONAL: Obtener el catálogo de videojuegos
    @Test
    void test04_ObtenerCatalogo() {
        List<Videojuego> catalogo = videojuegoService.obtenerTodos();
        
        // Verificamos que el sistema devuelva una lista (incluso si está vacía, no debe ser nula)
        assertNotNull(catalogo);
    }

    // 5. PRUEBA DE RENDIMIENTO: Tiempo de respuesta de consultas
    @Test
    void test05_RendimientoServicioVideojuegos() {
        // La consulta a la base de datos no debería tardar más de 500 milisegundos
        assertTimeout(Duration.ofMillis(500), () -> {
            videojuegoService.obtenerTodos();
        });
    }
}