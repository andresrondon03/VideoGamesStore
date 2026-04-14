package com.videogamesstore.api.entities;

import java.time.LocalDateTime;

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
@Table(name = "pedidos")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ped")
    private Integer id;

    @Column(name = "fecha_compra", insertable = false, updatable = false)
    private LocalDateTime fechaCompra;

    private Double total;

    @Column(nullable = false)
    private String estado; // 'Completado' o 'Cancelado'

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;
}