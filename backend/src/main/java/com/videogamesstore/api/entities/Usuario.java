package com.videogamesstore.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @Column(nullable = false, length = 15)
    private String nickname;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(name = "hashed_password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol", referencedColumnName = "id_rol")
    private Rol rol; // Ahora esto ya no debería estar en rojo
}