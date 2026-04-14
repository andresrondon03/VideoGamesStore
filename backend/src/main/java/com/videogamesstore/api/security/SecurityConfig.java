package com.videogamesstore.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()) // Habilita la conexión con el Frontend (React/Angular)
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Modo JWT
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/videojuegos/**").permitAll()
                
                // Rutas de Administrador
                .requestMatchers(HttpMethod.POST, "/api/videojuegos/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/videojuegos/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/videojuegos/**").hasAuthority("ADMIN")
                
                // Rutas de Cliente
                .requestMatchers("/api/pedidos/**").authenticated()

                // Permitir acceso público a la documentación de Swagger
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                
                .anyRequest().authenticated()
            );

        // Filtro antes del filtro de seguridad estándar de Spring
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}