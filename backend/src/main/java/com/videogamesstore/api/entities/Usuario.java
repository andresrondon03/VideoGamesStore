package com.videogamesstore.api.entities;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password; // Se guardará con BCrypt

    @Enumerated(EnumType.STRING)
    private RolUsuario rol; // CLIENTE o ADMINISTRADOR
}
