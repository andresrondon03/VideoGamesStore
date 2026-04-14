package com.videogamesstore.api.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "videojuegos")
@Data
public class Videojuego {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vj")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String titulo;
    
    @Column(length = 50)
    private String descripcion;
    
    @Column(name = "precio_base", nullable = false)
    private Double precioBase;
    
    @Column(nullable = false)
    private Integer stock;

    // Relación Muchos a Muchos con Categorías (Tabla pivote: vj_cat)
    @ManyToMany
    @JoinTable(
        name = "vj_cat",
        joinColumns = @JoinColumn(name = "id_vj"),
        inverseJoinColumns = @JoinColumn(name = "id_cat")
    )
    private List<Categoria> categorias;

    // Relación Muchos a Muchos con Plataformas (Tabla pivote: vj_plat)
    @ManyToMany
    @JoinTable(
        name = "vj_plat",
        joinColumns = @JoinColumn(name = "id_vj"),
        inverseJoinColumns = @JoinColumn(name = "id_plat")
    )
    private List<Plataforma> plataformas;
}