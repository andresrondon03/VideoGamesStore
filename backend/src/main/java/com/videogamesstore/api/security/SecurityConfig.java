package com.videogamesstore.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitamos CSRF porque usaremos APIs REST (Tokens)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Dejamos las rutas de videojuegos públicas por ahora para poder probar el CRUD
                .requestMatchers("/api/videojuegos/**").permitAll()
                // El endpoint de login también será público
                .requestMatchers("/api/auth/**").permitAll()
                // Cualquier otra petición requerirá autenticación
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
}