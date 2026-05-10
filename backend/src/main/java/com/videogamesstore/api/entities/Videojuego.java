package com.videogamesstore.api.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @Column(nullable = false, length = 100)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    // IMPORTANTE: Se llama precio en Java, pero se guarda en precio_base en SQL
    @Column(name = "precio_base", nullable = false)
    private Double precio;
    
    @Column(nullable = false)
    private Integer stock;

    // NUEVOS CAMPOS
    @Column(name = "descuento")
    private Integer descuento = 0;

    @Column(name = "imagen_url", columnDefinition = "TEXT")
    private String imagenUrl;

    // Relación Muchos a Muchos con Categorías (Tabla pivote: vj_cat)
    @ManyToMany
    @JoinTable(
        name = "vj_cat",
        joinColumns = @JoinColumn(name = "id_vj"),
        inverseJoinColumns = @JoinColumn(name = "id_cat")
    )
    @JsonIgnoreProperties("videojuegos") // Evita recursividad infinita en JSON
    private List<Categoria> categorias;

    // Relación Muchos a Muchos con Plataformas (Tabla pivote: vj_plat)
    @ManyToMany
    @JoinTable(
        name = "vj_plat",
        joinColumns = @JoinColumn(name = "id_vj"),
        inverseJoinColumns = @JoinColumn(name = "id_plat")
    )
    @JsonIgnoreProperties("videojuegos") // Evita recursividad infinita en JSON
    private List<Plataforma> plataformas;
}