package com.videogamesstore.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "videojuegos")
@Data // Lombok para getters/setters
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    
    private String descripcion;
    private Double precioBase;
    private Integer stock;
    private String plataforma;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
